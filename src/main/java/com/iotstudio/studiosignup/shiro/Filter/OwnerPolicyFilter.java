package com.iotstudio.studiosignup.shiro.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class OwnerPolicyFilter extends AccessControlFilter{


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Integer currentUserId = (Integer) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Map pathVariableMap = (Map)servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);//pathvariable参数列表
        Integer targetUserId = (Integer)pathVariableMap.get("userId");
        return targetUserId.equals(currentUserId);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write("{'msg':'没有权限！'}");
        return false;
    }
}
