package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.PermissionActionConstant;
import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.entity.User;
import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.util.BindingResultHandlerUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = RoleNameConstant.USER )
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    public static final String entity = "user";

//    @GetMapping(value = entity)
//    public ResponseModel userList(){
//        return userService.selectAll();
//    }

    /**
     * 分页查询所有用户
     * @param page 页码
     * @param size 每一页的数量
     */
    @RequiresPermissions(value = entity + ":find")
    @GetMapping(value = entity)
    public ResponseModel userListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "size",defaultValue = "10") Integer size){
        return userService.selectAllByPage(page-1,size);
    }

    /**
     * 根据用户id查询一个用户
     * @param id 用户id
     * @return 该用户信息
     */
    @RequiresPermissions(value = entity + ":find")
    @GetMapping(value = entity+"/{userId}")
    public ResponseModel userFindOneById(@PathVariable("id") Integer id){
        return userService.selectOneById(id);
    }

    /**
     * 新增用户
     * @param user 用户
     * @param roleName 角色
     * @return 用户信息
     */
    @RequiresPermissions(value = entity + ":add")
    @PostMapping(value = entity)
    public ResponseModel userAddOne(@Valid User user,
                                    @RequestParam("roleName") String roleName,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        return userService.addOne(user,roleName);
    }

    @RequiresPermissions(value = entity + PermissionActionConstant.UPDATE )
    @PutMapping(value = entity)
    public ResponseModel userUpdateOne(@Valid User user,
                                       BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        return userService.updateOne(user);
    }

    @RequiresPermissions(value = entity + ":delete")
    @DeleteMapping(value = entity+"/{userId}")
    public ResponseModel userDeleteOne(@PathVariable("id") Integer id){
        return userService.deleteOneById(id);
    }

}
