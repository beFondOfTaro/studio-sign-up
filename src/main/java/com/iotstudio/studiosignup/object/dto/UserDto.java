package com.iotstudio.studiosignup.object.dto;

import com.iotstudio.studiosignup.object.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Integer id;
    private List<Role> roleList;//角色
    private String username;//用户名
    private String realName;//真实姓名
    private String phone;//电话
}
