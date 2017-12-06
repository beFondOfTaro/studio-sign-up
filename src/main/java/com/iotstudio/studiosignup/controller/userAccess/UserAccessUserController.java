package com.iotstudio.studiosignup.controller.userAccess;

import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.util.CookieUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user/{user_id}")
public class UserAccessUserController {
    private final String entity = "user";
    @Autowired
    private UserService userService;

    @DeleteMapping(value = entity+"/logout")
    public ResponseModel userLogout(HttpServletRequest request){
        String userId = CookieUtil.getCookieValueByName(request,CookieUtil.clientIdKey);
        return userService.logout(userId);
    }
}
