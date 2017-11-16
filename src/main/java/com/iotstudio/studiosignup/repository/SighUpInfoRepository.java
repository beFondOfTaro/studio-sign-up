package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.SighUpInfo;
import com.iotstudio.studiosignup.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SighUpInfoRepository extends JpaRepository<SighUpInfo,Integer> {
    void deleteByStudent(Student student);
}
