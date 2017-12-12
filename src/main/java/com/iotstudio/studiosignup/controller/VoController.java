package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.service.VoService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RoleNameConstant.API_VERSION)
public class VoController {

    @Autowired
    private VoService voService;

    @GetMapping("getUserStudentInfo")
    public ResponseModel getUserStudentInfo(){
        return voService.getUserStudentInfo(Integer.valueOf ((String) (SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal())));
    }
}
