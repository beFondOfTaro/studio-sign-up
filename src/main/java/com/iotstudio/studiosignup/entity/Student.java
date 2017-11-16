package com.iotstudio.studiosignup.entity;

import com.iotstudio.studiosignup.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Student extends BaseEntity{

    @NotNull
    private String username;//用户名

    @NotNull
    private String password;//密码

    @NotNull
    private String realName;//真实姓名

    @NotNull
    private String studentNumber;//学号

    @NotNull
    private String major;//专业

    @NotNull
    private String phone;//电话

    @NotNull
    private String qqNumber;//QQ号

    public Student() {
    }

    public Student(String username, String password, String realName, String studentNumber, String major, String phone, String qqNumber) {
        super();
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.studentNumber = studentNumber;
        this.major = major;
        this.phone = phone;
        this.qqNumber = qqNumber;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", major='" + major + '\'' +
                ", phone='" + phone + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                "} " + super.toString();
    }
}
