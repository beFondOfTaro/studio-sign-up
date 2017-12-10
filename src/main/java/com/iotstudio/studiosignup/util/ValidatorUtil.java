package com.iotstudio.studiosignup.util;

import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


public class ValidatorUtil {

    //根据用户身份验证是否有查询权限
    public static boolean ValidateQueryByRole(User targetUser){
        Subject subject = SecurityUtils.getSubject();
        String currentUserId = (String) subject.getPrincipals().getPrimaryPrincipal();
        boolean targetIsCurrentUser = currentUserId.equals(targetUser.getId().toString());//查询的是否为当前用户
        //管理员
        if (subject.hasRole(RoleNameConstant.ADMIN)){

        }
        //教师
        else if (subject.hasRole(RoleNameConstant.TEACHER)){
            //只能查询学生和自己的
            if (!targetUser.getRoleList().get(0).getName().equals(RoleNameConstant.STUDENT) && !targetIsCurrentUser){
                return false;
            }
        }
        //学生只能查询自己的
        else if (subject.hasRole(RoleNameConstant.STUDENT)){
            if (!targetIsCurrentUser){
                return false;
            }
        }
        return true;
    }

    public static boolean validateUpdateByRole(User targetUser){
        Subject subject = SecurityUtils.getSubject();
        String currentUserId = (String) subject.getPrincipals().getPrimaryPrincipal();
        boolean targetIsCurrentUser = currentUserId.equals(targetUser.getId().toString());//查询的是否为当前用户
        //不是管理员只能更新自己的信息
        if (!subject.hasRole(RoleNameConstant.ADMIN)){
            if (!targetIsCurrentUser){
                return false;
            }
        }
        return true;
    }
}
