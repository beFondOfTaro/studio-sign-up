package com.iotstudio.studiosignup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ProjectType {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String name;//项目类型名

    public ProjectType() {
    }

    public ProjectType(String name) {
        this.name = name;
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
}
