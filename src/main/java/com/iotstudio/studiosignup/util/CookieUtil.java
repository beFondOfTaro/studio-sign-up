package com.iotstudio.studiosignup.util;

import com.iotstudio.studiosignup.shiro.token.TokenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name  cookie名字
     * @param value cookie值
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        if(maxAge>0)  cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie，默认过期时间为token的过期时间
     * @param response
     * @param k
     * @param v
     */
    public static void addCookie(HttpServletResponse response,String k, String v){
        addCookie(response,k,v,(int) TokenUtil.DEFAULT_EXPIRATION_TIME*86400);//转换单位为秒
    }
    /**
     * 根据名字获取cookie
     * @param request 请求
     * @param name cookie名字
     * @return cookie
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        return cookieMap.getOrDefault(name,null);
    }

    /**
     * 根据名字获取cookie的值
     * @param request 请求
     * @param name cookie名字
     * @return cookie值
     */
    public static String getCookieValueByName(HttpServletRequest request,String name){
        Cookie cookie = getCookieByName(request,name);
        if (cookie == null){
            throw new NullPointerException("不存在键为"+ name + "的cookie");
        }else {
            return cookie.getValue();
        }
    }


    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}
