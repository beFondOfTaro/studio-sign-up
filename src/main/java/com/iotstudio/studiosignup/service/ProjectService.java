package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.object.entity.Project;
import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface ProjectService extends BaseService<Project> {

    ResponseModel findProjectsByUserId(Integer userId);

    ResponseModel findProjectByUserIdAndProjectId(Integer userId,Integer projectId);
}
