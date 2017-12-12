package com.iotstudio.studiosignup.object.vo;

import lombok.Data;

@Data
public class SighUpInfoVo {
    private String username;
    private String realName;
    private String major;
    private String phone;
    private Integer checkCode;
    private String personalIntroduction;
    private String projectName;
}
