package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.object.entity.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TeacherInfoRepository extends JpaRepository<TeacherInfo,Integer> {
    TeacherInfo findTeacherInfoByUserId(Integer userId);

    void deleteTeacherInfoByUserId(Integer userId);

    @Modifying
    @Query("update TeacherInfo as t set t.teacherNumber = ?3 where t.userId = ?1 and t.id = ?2")
    Integer updateTeacherInfoByUserIdAndId(Integer userId,Integer id, String teacherNumber);
}
