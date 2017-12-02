package com.iotstudio.studiosignup.shiro;

import com.iotstudio.studiosignup.util.CookieUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StatelessAccessControllerFilter extends AccessControlFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatelessAccessControllerFilter.class);
    private static final String clientDigestKey = "digest";
    private static final String clientIdKey = "client_id";
    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * <p>
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * <p>
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，会往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        LOGGER.info("StatelessAuthcFilter.isAccessAllowed()");
        return true;
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        LOGGER.info("StatelessAuthcFilter.onAccessDenied()");
        try {
            //1、客户端生成的消息摘要
            String clientDigest = CookieUtil.getCookieValueByName((HttpServletRequest) servletRequest, clientDigestKey);
            //2、客户端传入的用户身份
            String userId = CookieUtil.getCookieValueByName((HttpServletRequest) servletRequest, clientIdKey);
            //3、添加用于生成消息摘要的参数列表
            Map<String, String> params = new HashMap<>();
            params.put("currentTime", String.valueOf(new Date().getTime()));//当前时间
            //4、生成无状态Token
            StatelessAuthenticationToken token = new StatelessAuthenticationToken(userId, params, clientDigest);

            //5、委托给Realm进行登录
            getSubject(servletRequest, servletResponse).login(token);
        } catch (NullPointerException e) {
            LOGGER.error("已拦截请求！"+ clientIdKey + "或" + clientDigestKey+ "不能为空！");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(servletResponse);
            return false;
        }
        return true;
    }

    //登录失败时默认返回401 状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write("login error");
    }
}
