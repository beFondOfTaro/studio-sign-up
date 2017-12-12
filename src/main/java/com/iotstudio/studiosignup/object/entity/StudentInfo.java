package com.iotstudio.studiosignup.object.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StudentInfo{

    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;

    @NotNull
    private String studentNumber;//学号

    @NotNull
    private String major;//专业

    @NotNull
    private String qqNumber;//QQ号

    private String photo;//照片

    public StudentInfo() {
    }

    public StudentInfo(Integer userId, String studentNumber, String major, String qqNumber, String photo) {
        this.userId = userId;
        this.studentNumber = studentNumber;
        this.major = major;
        this.qqNumber = qqNumber;
        this.photo = photo;
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

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", studentNumber='" + studentNumber + '\'' +
                ", major='" + major + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
