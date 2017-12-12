package com.iotstudio.studiosignup.object.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//序列化格式
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")//前端参数传递转换
    @Column(insertable = false,updatable = false)//使该字段在新增和修改时不可用
    private Date createdTime;//创建时间

    public BaseEntity() {
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                '}';
    }
}
