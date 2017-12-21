package com.waterhub.web.repository;

import com.waterhub.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(String email);
}
