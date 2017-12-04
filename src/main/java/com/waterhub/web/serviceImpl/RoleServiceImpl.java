package com.waterhub.web.serviceImpl;

import com.waterhub.web.model.Role;
import com.waterhub.web.repository.RoleRepository;
import com.waterhub.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Long countAll() {
        return roleRepository.count();
    }

    @Override
    public Long countUsersByRoleId(long roleId) {
        return roleRepository.countUsersByRoleId(roleId);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Boolean isExist(Role role) {
        return isExist(role.getId());
    }

    @Override
    public Boolean isExist(long roleId) {
        return roleRepository.exists(roleId);
    }

    @Override
    public Role findOne(Role role) {
        return findOne(role.getId());
    }

    @Override
    public Role findOne(long roleId) {
        return roleRepository.findOne(roleId);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public void delete(long roleId) {
        roleRepository.delete(roleId);
    }
}
