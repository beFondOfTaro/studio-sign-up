package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
