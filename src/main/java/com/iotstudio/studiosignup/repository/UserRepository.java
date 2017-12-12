package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.object.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
