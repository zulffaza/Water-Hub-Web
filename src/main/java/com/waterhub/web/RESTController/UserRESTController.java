package com.waterhub.web.RESTController;

import com.waterhub.web.model.MyResponse;
import com.waterhub.web.model.User;
import com.waterhub.web.service.UserService;
import com.waterhub.web.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRESTController {

    private UserService userService;

    private final String[] SORT_PROPERTIES = {
            "email",
            "firstName",
            "lastName",
            "birthDate",
            "country",
            "city",
            "address",
            "createdAt"
    };

    private final String[] SORT_DIRECTION = {
            "asc",
            "desc"
    };

    @Autowired
    public UserRESTController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user/count/all")
    public MyResponse<Long> getCountAll() {
        String message = "Get user success";
        Long data = userService.countAll();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/user/all")
    public MyResponse<List<User>> findAll() {
        String message = "Find users success";
        List<User> data = userService.findAll();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/user/page/{page}")
    public MyResponse<List<User>> findAllPageable(@PathVariable Integer page,
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

        if (sort >= 1 && sort <= 16)
            sortDirectionIndex = sort - 1;

        ArrayList<String> sortProperties = new ArrayList<>();
        sortProperties.add(SORT_PROPERTIES[sortPropertiesIndex]);

        Sort.Direction sortDirection = Sort.Direction.fromString(SORT_DIRECTION[sortDirectionIndex]);

        Sort sortObj = new Sort(sortDirection, sortProperties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortObj);

        String message = "Find users success";
        List<User> data = userService.findAll(pageRequest).getContent();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/user/{userId}")
    public MyResponse<User> findOne(@PathVariable Integer userId) {
        User data = userService.findOne(userId);
        String message = data != null ? "Find user success" : "Find user failed";

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/user/is-exist/{userId}")
    public MyResponse<Boolean> isExist(@PathVariable Integer userId) {
        Boolean data = userService.isExist(userId);
        String message = data ? "User is exist" : "User doesn't exist";

        return new MyResponse<>(message, data);
    }

    @PostMapping("/api/user/save")
    public MyResponse<Integer> save(@RequestBody User user) {
        String message;
        Integer data;

        try {
            boolean isEdit = user.getId() != 0;
            message = isEdit ? "User edit " : "User add ";

            if (isEdit) {
                User oldData = userService.findOne(user);

                if (!user.getPassword().isEmpty())
                    user.setPassword(User.passwordEncoder(user.getPassword()));
                else
                    user.setPassword(oldData.getPassword());
            } else
                user.setPassword(User.passwordEncoder(user.getPassword()));

            user = userService.save(user);

            boolean isSuccess = user != null;
            message += isSuccess ? "success" : "failed";
            data = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "User add failed - User already exists";
            data = 0;
        } catch (Exception e) {
            message = "User add failed - Internal Server Error";
            data = 0;
        }

        return new MyResponse<>(message, data);
    }

    @DeleteMapping("/api/user/{userId}")
    public MyResponse<Integer> delete(@PathVariable Integer userId) {
        String message;
        Integer data;

        try {
            userService.delete(userId);

            message = "Delete user success";
            data = 1;
        } catch (EmptyResultDataAccessException e) {
            message = "Delete user failed - User not found";
            data = 0;
        } catch (DataIntegrityViolationException e) {
            message = "Delete user failed - User in use";
            data = 0;
        } catch (Exception e) {
            message = "Delete user failed - Internal Server Error";
            data = 0;
        }

        return new MyResponse<>(message, data);
    }
}
