package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.PermissionActionConstant;
import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.service.PermissionService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = RoleNameConstant.API_VERSION)
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    public static final String entity = "permission";

//    @GetMapping(value = entity)
//    public ResponseModel permissionList(){
//        return permissionService.selectAll();
//    }

    /**
     * 分页查询所有权限
     * @param page 页码
     * @param size 每一页的数量
     */
    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity)
    public ResponseModel permissionListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "size",defaultValue = "10") Integer size){
        return permissionService.selectAllByPage(page-1,size);
    }

    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity+"/{permissionId}")
    public ResponseModel permissionFindOneById(@PathVariable("permissionId") Integer id){
        return permissionService.selectOneById(id);
    }

}
