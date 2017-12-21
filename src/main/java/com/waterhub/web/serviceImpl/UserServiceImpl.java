package com.waterhub.web.serviceImpl;

import com.waterhub.web.model.User;
import com.waterhub.web.repository.UserRepository;
import com.waterhub.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long countAll() {
        return userRepository.count();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Boolean isExist(User user) {
        return isExist(user.getId());
    }

    @Override
    public Boolean isExist(long userId) {
        return userRepository.exists(userId);
    }

    @Override
    public User findOne(User user) {
        return findOne(user.getId());
    }

    @Override
    public User findOne(long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User findOne(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(long userId) {
        userRepository.delete(userId);
    }
}
