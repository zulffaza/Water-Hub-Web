package com.waterhub.web.RESTController;

import com.waterhub.web.model.MyResponse;
import com.waterhub.web.model.Role;
import com.waterhub.web.service.RoleService;
import com.waterhub.web.serviceImpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleRESTController {

    private RoleService roleService;

    private final String[] SORT_PROPERTIES = {
            "name"
    };

    private final String[] SORT_DIRECTION = {
            "asc",
            "desc"
    };

    @Autowired
    public RoleRESTController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/api/role/count/all")
    public MyResponse<Long> getCountAll() {
        String message = "Get count success";
        Long data = roleService.countAll();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/role/count/users")
    public MyResponse<Long> getCountUsers(@RequestParam Long roleId) {
        String message = "Get count success";
        Long data = roleService.countUsersByRoleId(roleId);

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/role/all")
    public MyResponse<List<Role>> findAll() {
        String message = "Find roles success";
        List<Role> data = roleService.findAll();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/role/page/{page}")
    public MyResponse<List<Role>> findAllPageable(@PathVariable Integer page,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                  @RequestParam(required = false, defaultValue = "1") Integer sort) {
        int sortPropertiesIndex = 0;
        int sortDirectionIndex = 0;

        if (page <= 0)
            page = 0;

        if (page > 0)
            page -= 1;

        if (pageSize <= 0)
            pageSize = 10;

        if (sort >= 1 && sort <= 2)
            sortDirectionIndex = sort - 1;

        ArrayList<String> sortProperties = new ArrayList<>();
        sortProperties.add(SORT_PROPERTIES[sortPropertiesIndex]);

        Sort.Direction sortDirection = Sort.Direction.fromString(SORT_DIRECTION[sortDirectionIndex]);

        Sort sortObj = new Sort(sortDirection, sortProperties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortObj);

        String message = "Find roles success";
        List<Role> data = roleService.findAll(pageRequest).getContent();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/role/{roleId}")
    public MyResponse<Role> findOne(@PathVariable Integer roleId) {
        Role data = roleService.findOne(roleId);
        String message = data != null ? "Find role success" : "Find role failed";

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/role/is-exist/{roleId}")
    public MyResponse<Boolean> isExist(@PathVariable Integer roleId) {
        Boolean data = roleService.isExist(roleId);
        String message = data ? "Role is exist" : "Role doesn't exist";

        return new MyResponse<>(message, data);
    }

    @PostMapping("/api/role/save")
    public MyResponse<Integer> save(@RequestBody Role role) {
        String message;
        Integer data;

        if (role.getId() != 0) {
            long usersCount = roleService.countUsersByRoleId(role.getId());

            if (usersCount == 0) {
                role = roleService.save(role);

                boolean isSuccess = role != null;
                message = isSuccess ? "Role edit success" : "Role edit failed";
                data = isSuccess ? 1 : 0;
            } else {
                message = "Role edit failed - Role in use";
                data = 0;
            }
        } else {
            try {
                role = roleService.save(role);

                boolean isSuccess = role != null;
                message = isSuccess ? "Role add success" : "Role add failed";
                data = isSuccess ? 1 : 0;
            } catch (DataIntegrityViolationException e) {
                message = "Role add failed - Role already exists";
                data = 0;
            } catch (Exception e) {
                message = "Role add failed - Internal Server Error";
                data = 0;
            }
        }

        return new MyResponse<>(message, data);
    }

    @DeleteMapping("/api/role/{roleId}")
    public MyResponse<Integer> delete(@PathVariable Integer roleId) {
        String message;
        Integer data;

        try {
            roleService.delete(roleId);

            message = "Delete role success";
            data = 1;
        } catch (EmptyResultDataAccessException e) {
            message = "Delete role failed - Role not found";
            data = 0;
        } catch (DataIntegrityViolationException e) {
            message = "Delete role failed - Role in use";
            data = 0;
        } catch (Exception e) {
            message = "Delete role failed - Internal Server Error";
            data = 0;
        }

        return new MyResponse<>(message, data);
    }
}
