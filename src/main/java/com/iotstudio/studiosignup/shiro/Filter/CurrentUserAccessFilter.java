package com.iotstudio.studiosignup.shiro.Filter;

import com.iotstudio.studiosignup.util.CookieUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CurrentUserAccessFilter extends AdviceFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println(request.getParameter(CookieUtil.clientIdKey));
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        return request.getParameter(CookieUtil.clientIdKey)==SecurityUtils.getSubject().getPrincipal();
    }
}
