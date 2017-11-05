package com.iotstudio.studiosignup.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class SighUpInfo extends BaseEntity {

    private Integer projectTypeId;//项目类型id

    private Integer studentId;//学生id

    @NotNull
    private String personalIntroduction;//个人介绍

    public SighUpInfo() {
    }

    public SighUpInfo(Date createdDate, Integer projectTypeId, Integer studentId, String personalIntroduction) {
        super(createdDate);
        this.projectTypeId = projectTypeId;
        this.studentId = studentId;
        this.personalIntroduction = personalIntroduction;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getPersonalIntroduction() {
        return personalIntroduction;
    }

    public void setPersonalIntroduction(String personalIntroduction) {
        this.personalIntroduction = personalIntroduction;
    }
}
