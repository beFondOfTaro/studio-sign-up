package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.entity.SighUpInfo;
import com.iotstudio.studiosignup.util.model.ResponseModel;

import javax.servlet.http.HttpServletResponse;

public interface SighUpInfoService extends BaseService<SighUpInfo> {
    ResponseModel addOne(SighUpInfo sighUpInfo,Integer userId, String projectName);
    ResponseModel updateOne(SighUpInfo sighUpInfo,Integer userId, String projectName);
    ResponseModel addOne(SighUpInfo sighUpInfo,String username, String projectName);
    ResponseModel updateOne(SighUpInfo sighUpInfo,String username, String projectName);
    ResponseModel selectSighUpInfoByUserIdAndProjectId(Integer userId,Integer projectId,HttpServletResponse response);
}
