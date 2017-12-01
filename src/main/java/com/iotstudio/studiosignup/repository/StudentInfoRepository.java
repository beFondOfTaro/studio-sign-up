package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInfoRepository extends JpaRepository<StudentInfo,Integer> {
}
