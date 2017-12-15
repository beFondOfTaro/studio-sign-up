package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface StudentInfoService extends BaseService<StudentInfo> {

    ResponseModel addOne(StudentInfo studentInfo, String userId, MultipartFile photoFile);

    ResponseModel updateOneByUserId(StudentInfo studentInfo, String userId, MultipartFile photoFile);

    ResponseModel deleteOneByUserId(String UserId);

    ResponseModel findOneByUserId(String userId);
}
