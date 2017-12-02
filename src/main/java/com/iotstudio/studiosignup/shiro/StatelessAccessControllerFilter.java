package com.iotstudio.studiosignup.shiro;

import com.iotstudio.studiosignup.shiro.token.TokenUtil;
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
    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，会往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        LOGGER.info("StatelessAuthcFilter.isAccessAllowed()");
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            //1、客户端生成的消息摘要
            String clientDigest = CookieUtil.getCookieValueByName(httpServletRequest, CookieUtil.clientDigestKey);
            //2、客户端传入的用户身份
            String clientId = CookieUtil.getCookieValueByName(httpServletRequest, CookieUtil.clientIdKey);
            //3、添加用于生成消息摘要的参数列表
            //4、生成无状态Token
            StatelessAuthenticationToken token = new StatelessAuthenticationToken(clientId, clientDigest);
            //5、委托给Realm进行登录
            getSubject(servletRequest, servletResponse).login(token);
            //登录成功后设置cookie保存登录状态
            TokenUtil.setCookie(httpServletResponse,CookieUtil.clientIdKey, clientId);
            TokenUtil.setCookie(httpServletResponse,CookieUtil.clientDigestKey,clientDigest);
        } catch (NullPointerException e) {
            LOGGER.error("已拦截请求！"+ CookieUtil.clientIdKey + "或" + CookieUtil.clientDigestKey+ "不能为空！");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        LOGGER.info("StatelessAuthcFilter.onAccessDenied()");
        onLoginFail(servletResponse);
        return false;
    }

    //登录失败时默认返回401 状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write("login error");
    }

}
