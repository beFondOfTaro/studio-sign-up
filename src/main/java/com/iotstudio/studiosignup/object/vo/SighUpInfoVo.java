package com.iotstudio.studiosignup.object.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SighUpInfoVo {
    private Integer userId;
    private Integer sighUpInfoId;
    private String username;
    private String realName;
    private String major;
    private String phone;
    private Integer checkCode;
    private String personalIntroduction;
    private String projectName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//序列化格式
    private Date createdTime;
}
