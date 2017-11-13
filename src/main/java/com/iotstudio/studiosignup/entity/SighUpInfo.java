package com.iotstudio.studiosignup.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class SighUpInfo extends BaseEntity {

    @ManyToOne(targetEntity = Project.class,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;//项目类型

    @ManyToOne(targetEntity = Student.class,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;//学生

    @NotNull
    private String personalIntroduction;//个人介绍

    public SighUpInfo() {
    }

    public SighUpInfo(Date createdTime, Project project, Student student, String personalIntroduction) {
        super(createdTime);
        this.project = project;
        this.student = student;
        this.personalIntroduction = personalIntroduction;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getPersonalIntroduction() {
        return personalIntroduction;
    }

    public void setPersonalIntroduction(String personalIntroduction) {
        this.personalIntroduction = personalIntroduction;
    }
}
