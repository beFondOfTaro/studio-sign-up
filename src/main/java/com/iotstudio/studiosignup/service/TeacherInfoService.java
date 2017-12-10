package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.entity.TeacherInfo;
import com.iotstudio.studiosignup.util.model.ResponseModel;

import javax.servlet.http.HttpServletResponse;

public interface TeacherInfoService extends BaseService<TeacherInfo> {
    ResponseModel addTeacherInfoByUserId(TeacherInfo teacherInfo, String userId);

    ResponseModel updateOneByUserIdAndTeacherId(TeacherInfo teacherInfo, String userId);

    ResponseModel deleteOneByUserId(String userId,String teacherId);

    ResponseModel findTeacherInfoListByUserId(String userId);
}
