package com.iotstudio.studiosignup.object.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TeacherInfo{

    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;

    @NotNull
    private String teacherNumber;//工号

    public TeacherInfo() {
    }

    public TeacherInfo(Integer userId, String teacherNumber) {
        this.userId = userId;
        this.teacherNumber = teacherNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", teacherNumber='" + teacherNumber + '\'' +
                '}';
    }
}
