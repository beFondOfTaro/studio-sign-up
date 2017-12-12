package com.iotstudio.studiosignup.object.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String name;

    /*
        使用@ManyToMany(mappedBy="roleList")来指定由映射主体类的roleList属性（或其getter方法）完成映射过程。
     */
    @NotNull
    @JsonIgnore//多对多关系在序列化时会造成无限循环，在其中一方添加该注解来使其序列化是时忽略该字段
    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    @JoinTable(name = "role_permission",
        joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Permission> permissionList;

    public Role() {
    }

    public Role(String name, List<User> userList, List<Permission> permissionList) {
        this.name = name;
        this.userList = userList;
        this.permissionList = permissionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userList=" + userList +
                ", permissionList=" + permissionList +
                '}';
    }
}
