package com.waterhub.web.serviceImpl;

import com.waterhub.web.model.Store;
import com.waterhub.web.repository.StoreRepository;
import com.waterhub.web.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Long countAll() {
        return storeRepository.count();
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Page<Store> findAll(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    @Override
    public Boolean isExist(Store store) {
        return isExist(store.getId());
    }

    @Override
    public Boolean isExist(long storeId) {
        return storeRepository.exists(storeId);
    }

    @Override
    public Store findOne(Store store) {
        return findOne(store.getId());
    }

    @Override
    public Store findOne(long storeId) {
        return storeRepository.findOne(storeId);
    }

    @Override
    public void delete(Store store) {
        storeRepository.delete(store);
    }

    @Override
    public void delete(long storeId) {
        storeRepository.delete(storeId);
    }
}
