package com.iotstudio.studiosignup.shiro.token;

import java.io.Serializable;

public class Token implements Serializable{
    private Integer userId;//用户id

    private String digest;//消息摘要

    public Token(Integer userId, String digest) {
        this.userId = userId;
        this.digest = digest;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
