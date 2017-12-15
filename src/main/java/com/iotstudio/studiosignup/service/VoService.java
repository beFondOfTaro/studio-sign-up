package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.object.entity.SighUpInfo;
import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface VoService {

    ResponseModel getUserStudentInfo(Integer userId);
    ResponseModel getUserTeacherInfo(Integer userId);
    ResponseModel getUserSighUpInfo(Integer sighUpInfoId);
    ResponseModel sighUp(SighUpInfo sighUpInfo, Integer userId, String projectName);
}
