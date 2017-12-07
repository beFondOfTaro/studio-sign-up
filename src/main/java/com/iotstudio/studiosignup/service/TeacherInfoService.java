package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.entity.TeacherInfo;
import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface TeacherInfoService extends BaseService<TeacherInfo> {
    ResponseModel addOneByUserId(TeacherInfo teacherInfo, String userId);

    ResponseModel updateOneByUserId(TeacherInfo teacherInfo, String userId);

    ResponseModel deleteOneByUserId(String userId);

    ResponseModel findOneByUserId(String userId);
}
