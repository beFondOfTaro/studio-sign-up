package com.iotstudio.studiosignup.controller.userAccess;

import com.iotstudio.studiosignup.constant.HttpParamKey;
import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserAccessUserController {
    private final String entity = "user";
    @Autowired
    private UserService userService;

    @DeleteMapping(value = entity+"/logout")
    public ResponseModel userLogout(@RequestHeader(HttpParamKey.CLIENT_ID) String userId){
        return userService.logout(userId);
    }
}
