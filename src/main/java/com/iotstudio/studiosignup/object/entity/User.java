package com.iotstudio.studiosignup.object.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class User extends BaseEntity {

    /*
        name属性：用于指定中间表数据表的表名（eg. name="category_item"指定中间表的表名为category_item）
    joinColumns属性：该注解用于指定映射主体类与中间表的映射关系。
    从javadoc中可以看到该属性的类型是JoinColumn[]，也就是说是一个@JoinColumn注解的集合。
    其中，@JoinColumn注解的name属性用于指定中间表的一个外键列的列名，
    该外键列参考映射主体类对应数据表的的主键列（如果该主键列的列名不是ID的时候，
    需要用referencedColumnName属性指定主键列的列名。如Category中的主键为“ID_ID”）；
    inverseJoinColumns属性：该注解用于指定对方（非映射主体类）实体类与中间表的映射关系。
    它也是一个JoinColumn[]类型。该属性的用法与joinColumns是一致的。
     */
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "user_role",
        joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<Role> roleList;

    @NotNull
    private String username;//用户名

    @NotNull
    private String password;//密码

    @NotNull
    private String realName;//真实姓名

    @NotNull
    private String phone;//电话

    public User() {
    }

    public User(List<Role> roleList, String username, String password, String realName, String phone) {
        this.roleList = roleList;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.phone = phone;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
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
                "roleList=" + roleList +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
                "} " + super.toString();
    }
}
