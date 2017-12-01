package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findRoleByName(String roleName);
}
