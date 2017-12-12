package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.entity.Role;
import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface RoleService extends BaseService<Role> {
    ResponseModel getPermissionListByRoleId(Integer roleId);
}
