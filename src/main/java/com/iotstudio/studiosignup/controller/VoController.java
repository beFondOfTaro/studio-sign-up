package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.object.entity.SighUpInfo;
import com.iotstudio.studiosignup.service.VoService;
import com.iotstudio.studiosignup.util.BindingResultHandlerUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(RoleNameConstant.API_VERSION)
public class VoController {

    @Autowired
    private VoService voService;

    @GetMapping("getUserStudentInfo")
    public ResponseModel getUserStudentInfo(){
        return voService.getUserStudentInfo(Integer.valueOf ((String) (SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal())));
    }

    @GetMapping("getUserTeacherInfo")
    public ResponseModel getUserTeacherInfo(){
        return voService.getUserTeacherInfo(Integer.valueOf ((String) (SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal())));
    }

    @GetMapping("getUserSighUpInfo/{sighUpInfoId}")
    public ResponseModel getUserSighUpInfo(@PathVariable("sighUpInfoId") Integer sighUpInfoId){
        return voService.getUserSighUpInfo(sighUpInfoId);
    }

    @PostMapping("sighUp")
    public ResponseModel sighUp(@Valid SighUpInfo sighUpInfo,
                                @RequestParam("client_id") Integer userId,
                                @RequestParam("projectName") String projectName,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        return voService.sighUp(sighUpInfo,userId,projectName);
    }
}
