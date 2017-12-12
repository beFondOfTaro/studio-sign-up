package com.iotstudio.studiosignup.object.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String name;

    private boolean ownerAvailable = true;//该权限对应的资源是否是允许资源所有者访问

    @JsonIgnore
    @ManyToMany(mappedBy = "permissionList")
    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }
}
