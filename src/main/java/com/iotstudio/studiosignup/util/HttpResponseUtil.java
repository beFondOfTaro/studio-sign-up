package com.iotstudio.studiosignup.util;

import com.iotstudio.studiosignup.util.model.ResponseModel;

import javax.servlet.http.HttpServletResponse;

public class HttpResponseUtil {
    public static ResponseModel noAuthority(HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return new ResponseModel("没有权限！");
    }

    public static ResponseModel incompleteHeaderParam(HttpServletResponse response,String headerParam){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ResponseModel("'" + headerParam + "'请求参数未提交!");
    }

    public static ResponseModel incompleteRequestParam(HttpServletResponse response,String requestParam){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ResponseModel("'" + requestParam + "'header参数未提交!");
    }
}
