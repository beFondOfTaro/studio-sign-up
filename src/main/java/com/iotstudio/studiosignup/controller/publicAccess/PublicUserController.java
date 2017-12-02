package com.iotstudio.studiosignup.controller.publicAccess;

import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.util.CookieUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PublicUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicUserController.class);
    private final String entity = "user";
    @Autowired
    private UserService userService;

    @PostMapping(value = entity+"/login")
    public ResponseModel userLogin(@RequestParam("username")String username,
                                   @RequestParam("password")String password){
        return userService.login(username,password);
    }

    @DeleteMapping(value = entity+"/logout")
    public ResponseModel userLogout(HttpServletRequest request){
        String userId = CookieUtil.getCookieValueByName(request,CookieUtil.clientIdKey);
        return userService.logout(userId);
    }
}
