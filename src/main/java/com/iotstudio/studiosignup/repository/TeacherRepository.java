package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
