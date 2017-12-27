package com.waterhub.web.RESTController;

import com.waterhub.web.model.MyPage;
import com.waterhub.web.model.MyResponse;
import com.waterhub.web.model.Store;
import com.waterhub.web.service.StoreService;
import com.waterhub.web.serviceImpl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StoreRESTController {

    private StoreService storeService;

    private final String[] SORT_PROPERTIES = {
            "name",
            "country",
            "city",
            "address",
            "status",
            "createdAt"
    };

    private final String[] SORT_DIRECTION = {
            "asc",
            "desc"
    };

    @Autowired
    public StoreRESTController(StoreServiceImpl storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/api/store/count/all")
    public MyResponse<Long> getCountAll() {
        String message = "Get store success";
        Long data = storeService.countAll();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/store/all")
    public MyResponse<List<Store>> findAll() {
        String message = "Find stores success";
        List<Store> data = storeService.findAll();

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/store/page/{page}")
    public MyResponse<MyPage<List<Store>>> findAllPageable(@PathVariable Integer page,
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

        if (sort >= 1 && sort <= 12) {
            boolean isPrime = sort % 2 == 0;
            sortDirectionIndex = isPrime ? 1 : 0;

            sortPropertiesIndex = (int) (Math.ceil((double) sort / 2) - 1);
        }

        ArrayList<String> sortProperties = new ArrayList<>();
        sortProperties.add(SORT_PROPERTIES[sortPropertiesIndex]);

        Sort.Direction sortDirection = Sort.Direction.fromString(SORT_DIRECTION[sortDirectionIndex]);

        Sort sortObj = new Sort(sortDirection, sortProperties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortObj);
        Page<Store> storePage = storeService.findAll(pageRequest);

        List<Store> data = storePage.getContent();
        String message = "Find stores success";

        MyPage<List<Store>> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(storePage.getTotalPages() == 0 ? 1 : storePage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort);
        myPage.setTotalElement(storePage.getTotalElements());
        myPage.setData(data);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/store/{storeId}")
    public MyResponse<Store> findOne(@PathVariable Integer storeId) {
        Store data = storeService.findOne(storeId);
        String message = data != null ? "Find store success" : "Find store failed";

        return new MyResponse<>(message, data);
    }

    @GetMapping("/api/store/is-exist/{storeId}")
    public MyResponse<Boolean> isExist(@PathVariable Integer storeId) {
        Boolean data = storeService.isExist(storeId);
        String message = data ? "Store is exist" : "Store doesn't exist";

        return new MyResponse<>(message, data);
    }

    @PostMapping("/api/store/save")
    public MyResponse<Integer> save(@RequestBody Store store) {
        String message;
        Integer data;

        try {
            boolean isEdit = store.getId() != 0;
            message = isEdit ? "Store edit " : "Store add ";

            store = storeService.save(store);

            boolean isSuccess = store != null;
            message += isSuccess ? "success" : "failed";
            data = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "Store add failed - Store already exists";
            data = 0;
        } catch (Exception e) {
            message = "Store add failed - Internal Server Error";
            data = 0;
        }

        return new MyResponse<>(message, data);
    }

    @DeleteMapping("/api/store/{storeId}")
    public MyResponse<Integer> delete(@PathVariable Integer storeId) {
        String message;
        Integer data;

        try {
            storeService.delete(storeId);

            message = "Delete store success";
            data = 1;
        } catch (EmptyResultDataAccessException e) {
            message = "Delete store failed - Store not found";
            data = 0;
        } catch (DataIntegrityViolationException e) {
            message = "Delete store failed - Store in use";
            data = 0;
        } catch (Exception e) {
            message = "Delete store failed - Internal Server Error";
            data = 0;
        }

        return new MyResponse<>(message, data);
    }
}
