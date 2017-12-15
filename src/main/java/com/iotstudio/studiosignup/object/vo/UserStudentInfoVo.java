package com.iotstudio.studiosignup.object.vo;

import lombok.Data;

@Data
public class UserStudentInfoVo {

    private Integer id;
    private Integer studentInfoId;
    private String username;
    private String realName;
    private String major;
    private String studentNumber;
    private String phone;
    private String qqNumber;
    private String photo;
}
