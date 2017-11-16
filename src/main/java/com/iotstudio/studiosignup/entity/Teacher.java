package com.iotstudio.studiosignup.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Teacher extends BaseEntity {

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

    public Teacher() {
    }

    public Teacher(String username, String password, String realName, String teacherNumber, String phone) {
        super();
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.teacherNumber = teacherNumber;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", teacherNumber='" + teacherNumber + '\'' +
                ", phone='" + phone + '\'' +
                "} " + super.toString();
    }
}
