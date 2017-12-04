package com.waterhub.web.repository;

import com.waterhub.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select count(u) from Role r left join r.users u where r.id = :roleId")
    public Long countUsersByRoleId(@Param("roleId") long roleId);
}
