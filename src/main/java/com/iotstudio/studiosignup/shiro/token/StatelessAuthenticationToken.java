package com.iotstudio.studiosignup.shiro.token;

import com.iotstudio.studiosignup.constant.HttpParamKey;
import com.iotstudio.studiosignup.util.CookieUtil;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于授权的Token对象：
 *
 * 用户身份即用户名；
 * 凭证即客户端传入的消息摘要。
 */
public class StatelessAuthenticationToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String userId;//用户身份即用户id；
    private Map<String,String> params = new HashMap<>();//参数.
    private String clientDigest;//凭证即客户端传入的消息摘要。

    public StatelessAuthenticationToken() {
        params.put(HttpParamKey.CURRENT_TIME, String.valueOf(new Date().getTime()));//当前时间
    }
    public StatelessAuthenticationToken(String userId, Map<String, String> params, String clientDigest) {
        super();
        this.userId = userId;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public StatelessAuthenticationToken(String userId, String clientDigest) {
        super();
        this.userId = userId;
        this.clientDigest = clientDigest;
    }

    public StatelessAuthenticationToken(String userId) {
        super();
        this.userId = userId;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }
}
