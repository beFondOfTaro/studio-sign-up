package com.iotstudio.studiosignup.controller.publicAccess;

import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.object.entity.TeacherInfo;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.util.BindingResultHandlerUtil;
import com.iotstudio.studiosignup.util.HttpResponseUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("pub")
public class PublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublicController.class);
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseModel userLogin(@RequestParam("username")String username,
                                   @RequestParam("password")String password,
                                   HttpServletResponse response){
        return userService.login(response,username,password);
    }

    /**
     * 注册帐号
     * @param user 用户
     * @param roleName 角色
     * @return 用户信息
     */
    @PostMapping(value = "register")
    public ResponseModel userRegister(@Valid User user,
                                      @RequestParam("roleName") String roleName,
                                      HttpServletResponse response,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return BindingResultHandlerUtil.onError(bindingResult);
        }
        //不允许注册管理员账户
        if (roleName.equals(RoleNameConstant.ADMIN)){
            return HttpResponseUtil.noAuthority(response);
        }
        return userService.addOne(user,roleName);
    }

    @PostMapping(value = "studentRegister")
    public ResponseModel studentRegister(@Valid User user, @Valid StudentInfo studentInfo,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return BindingResultHandlerUtil.onError(bindingResult);
        }
        return userService.studentRegister(user,studentInfo);
    }

    @PostMapping(value = "teacherRegister")
    public ResponseModel studentRegister(@Valid User user, @Valid TeacherInfo teacherInfo,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return BindingResultHandlerUtil.onError(bindingResult);
        }
        return userService.teacherRegister(user,teacherInfo);
    }
}
