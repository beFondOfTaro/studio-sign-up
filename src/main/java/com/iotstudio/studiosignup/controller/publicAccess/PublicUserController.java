package com.iotstudio.studiosignup.controller.publicAccess;

import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PublicUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicUserController.class);
    private final String entity = "user";
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseModel userLogin(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   HttpServletResponse response){
        return userService.login(response,username,password);
    }
}
