package com.iotstudio.studiosignup.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class SighUpInfo extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;//项目类型

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;//学生

    @NotNull
    private String personalIntroduction;//个人介绍

    public SighUpInfo() {
    }

    public SighUpInfo(Project project, User user, String personalIntroduction) {
        this.project = project;
        this.user = user;
        this.personalIntroduction = personalIntroduction;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPersonalIntroduction() {
        return personalIntroduction;
    }

    public void setPersonalIntroduction(String personalIntroduction) {
        this.personalIntroduction = personalIntroduction;
    }

    @Override
    public String toString() {
        return "SighUpInfo{" +
                "project=" + project +
                ", user=" + user +
                ", personalIntroduction='" + personalIntroduction + '\'' +
                "} " + super.toString();
    }
}
