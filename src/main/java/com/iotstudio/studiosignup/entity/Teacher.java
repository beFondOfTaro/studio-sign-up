package com.iotstudio.studiosignup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String username;//用户名

    @NotNull
    private String password;//密码

    @NotNull
    private String realName;//真实姓名

    @NotNull
    private String teacherNumber;//工号

    @NotNull
    private String phone;//电话

    private Date createdTime;//创建时间

    public Teacher() {
    }

    public Teacher(String username, String password, String realName, String teacherNumber, String phone, Date createdTime) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.teacherNumber = teacherNumber;
        this.phone = phone;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", teacherNumber='" + teacherNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
