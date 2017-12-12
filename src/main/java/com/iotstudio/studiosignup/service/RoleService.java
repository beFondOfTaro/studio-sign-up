package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.object.entity.Role;
import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface RoleService extends BaseService<Role> {
    ResponseModel getPermissionListByRoleId(Integer roleId);
}
