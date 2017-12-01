package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
