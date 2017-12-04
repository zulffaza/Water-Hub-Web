package com.waterhub.web.service;

import com.waterhub.web.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    public Long countAll();
    public User save(User user);
    public List<User> findAll();
    public Page<User> findAll(Pageable pageable);
    public Boolean isExist(User user);
    public Boolean isExist(long userId);
    public User findOne(User user);
    public User findOne(long userId);
    public void delete(User user);
    public void delete(long userId);
}
