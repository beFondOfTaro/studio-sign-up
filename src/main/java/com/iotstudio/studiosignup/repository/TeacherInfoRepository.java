package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.entity.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TeacherInfoRepository extends JpaRepository<TeacherInfo,Integer> {
    TeacherInfo findTeacherInfoByUserId(Integer userId);

    void deleteTeacherInfoByUserId(Integer userId);

    @Modifying
    @Query("update TeacherInfo as t set t.teacherNumber = ?2 where t.userId = ?1")
    Integer updateTeacherInfoByUserId(Integer userId, String teacherNumber);
}
