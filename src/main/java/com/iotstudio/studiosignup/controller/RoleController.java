package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.HttpParamKey;
import com.iotstudio.studiosignup.entity.Role;
import com.iotstudio.studiosignup.entity.User;
import com.iotstudio.studiosignup.service.RoleService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    private final String entity = "role";

//    @GetMapping(value = entity)
//    public ResponseModel roleList(){
//        return roleService.selectAll();
//    }

    /**
     * 分页查询所有角色
     * @param page 页码
     * @param size 每一页的数量
     */
    @GetMapping(value = entity)
    public ResponseModel roleListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "size",defaultValue = "10") Integer size){
        return roleService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel roleFindOneById(@PathVariable("id") Integer id){
        return roleService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel roleAddOne(Role role){
        return roleService.addOne(role);
    }

    @PutMapping(value = entity)
    public ResponseModel roleUpdateOne(Role role){
        return roleService.updateOne(role);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel roleDeleteOne(@PathVariable("id") Integer id){
        return roleService.deleteOneById(id);
    }

}
