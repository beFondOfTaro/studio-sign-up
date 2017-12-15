package com.iotstudio.studiosignup.repository;

import com.iotstudio.studiosignup.object.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentInfoRepository extends JpaRepository<StudentInfo,Integer> {

    @Modifying
    @Query("update StudentInfo as s set s.photo = ?1 where s.userId=?2")
    int updatePhotoByUserId(String photo, Integer userId);

    StudentInfo findByUserId(Integer userId);

    @Modifying
    @Query("update StudentInfo as s set " +
            "s.major = ?2," +
            "s.studentNumber = ?3," +
            "s.qqNumber = ?4," +
            "s.photo = ?5 " +
            "where s.userId = ?1")
    Integer updateByUserId(Integer userId,String major, String studentNumber, String qqNumber, String photo);

    void deleteByUserId(Integer userId);
}
