package com.waterhub.web.service;

import com.waterhub.web.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {

    public Long countAll();
    public Store save(Store store);
    public List<Store> findAll();
    public Page<Store> findAll(Pageable pageable);
    public Boolean isExist(Store store);
    public Boolean isExist(long storeId);
    public Store findOne(Store store);
    public Store findOne(long storeId);
    public void delete(Store store);
    public void delete(long storeId);
}
