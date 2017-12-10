package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
}
