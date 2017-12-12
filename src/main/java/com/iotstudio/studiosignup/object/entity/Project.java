package com.iotstudio.studiosignup.object.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 项目的实体类
 */
@Entity
public class Project extends BaseEntity {

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @NotNull
    private String name;//项目类型名

    @NotNull
    @Column(length = 600)
    private String introduction;

    public Project() {
    }

    public Project(User user, String name, String introduction) {
        this.user = user;
        this.name = name;
        this.introduction = introduction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Project{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                "} " + super.toString();
    }
}
