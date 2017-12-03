package com.iotstudio.studiosignup.dto;

import com.iotstudio.studiosignup.entity.Role;

public class UserDto {
    private Integer id;
    private Role role;//角色
    private String username;//用户名
    private String realName;//真实姓名
    private String phone;//电话

    public UserDto(Integer id, Role role, String username, String realName, String phone) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.realName = realName;
        this.phone = phone;
    }

    public UserDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
