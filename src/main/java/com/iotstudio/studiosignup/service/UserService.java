package com.iotstudio.studiosignup.service;

import com.iotstudio.studiosignup.entity.User;
import com.iotstudio.studiosignup.util.model.ResponseModel;

public interface UserService extends BaseService<User> {

    ResponseModel addOne(User user, String roleName);
    ResponseModel updateOne(User user,String roleName);
    ResponseModel login(String username,String password);
    ResponseModel logout(String userId);
}
