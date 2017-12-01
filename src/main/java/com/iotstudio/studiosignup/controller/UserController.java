package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.User;
import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin/{admin_id}")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private final String entity = "user";

//    @GetMapping(value = entity)
//    public ResponseModel userList(){
//        return userService.selectAll();
//    }

    /**
     * 分页查询所有用户
     * @param page 页码
     * @param size 每一页的数量
     */
    @GetMapping(value = entity)
    public ResponseModel userListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "size",defaultValue = "10") Integer size){
        return userService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel userFindOneById(@PathVariable("id") Integer id){
        return userService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel userAddOne(User user,@RequestParam("roleName") String roleName){
        return userService.addOne(user,roleName);
    }

    @PutMapping(value = entity)
    public ResponseModel userUpdateOne(User user,@RequestParam("roleName") String roleName){
        return userService.updateOne(user,roleName);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel userDeleteOne(@PathVariable("id") Integer id){
        return userService.deleteOneById(id);
    }

}
