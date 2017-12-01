package com.iotstudio.studiosignup.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    @NotNull
    @Column(unique = true)
    private String username;//用户名

    @NotNull
    private String password;//密码

    @NotNull
    private String realName;//真实姓名

    @NotNull
    private String phone;//电话

    public User() {
    }

    public User(Role role, String username, String password, String realName, String phone) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
                "} " + super.toString();
    }
}
