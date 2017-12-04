package com.waterhub.web.service;

import com.waterhub.web.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    public Long countAll();
    public Long countUsersByRoleId(long roleId);
    public Role save(Role role);
    public List<Role> findAll();
    public Page<Role> findAll(Pageable pageable);
    public Boolean isExist(Role role);
    public Boolean isExist(long roleId);
    public Role findOne(Role role);
    public Role findOne(long roleId);
    public void delete(Role role);
    public void delete(long roleId);
}
