package com.iotstudio.studiosignup.converter;

import com.iotstudio.studiosignup.object.entity.SighUpInfo;
import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.object.vo.SighUpInfoVo;

public class SighUpInfoVoConverter {
    public static SighUpInfoVo convert(SighUpInfo sighUpInfo, StudentInfo studentInfo, User user){
        SighUpInfoVo vo = new SighUpInfoVo();
        vo.setUserId(user.getId());
        vo.setSighUpInfoId(sighUpInfo.getId());
        vo.setUsername(user.getUsername());
        vo.setRealName(user.getRealName());
        vo.setMajor(studentInfo.getMajor());
        vo.setPhone(user.getPhone());
        vo.setPersonalIntroduction(sighUpInfo.getPersonalIntroduction());
        vo.setCheckCode(sighUpInfo.getCheckCode());
        vo.setProjectName(sighUpInfo.getProject().getName());
        vo.setCreatedTime(sighUpInfo.getCreatedTime());
        return vo;
    }
}
