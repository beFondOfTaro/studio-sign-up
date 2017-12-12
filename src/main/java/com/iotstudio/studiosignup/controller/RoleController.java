package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.PermissionActionConstant;
import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.object.entity.Role;
import com.iotstudio.studiosignup.service.RoleService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = RoleNameConstant.API_VERSION)
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    public static final String entity = "role";

//    @GetMapping(value = entity)
//    public ResponseModel roleList(){
//        return roleService.selectAll();
//    }

    /**
     * 分页查询所有角色
     * @param page 页码
     * @param size 每一页的数量
     */
    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity)
    public ResponseModel roleListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "size",defaultValue = "10") Integer size){
        return roleService.selectAllByPage(page-1,size);
    }

    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity+"/{roleId}")
    public ResponseModel roleFindOneById(@PathVariable("roleId") Integer id){
        return roleService.selectOneById(id);
    }

    @RequiresPermissions(entity + PermissionActionConstant.ADD)
    @PostMapping(value = entity)
    public ResponseModel roleAddOne(Role role){
        return roleService.addOne(role);
    }

    @RequiresPermissions(entity + PermissionActionConstant.UPDATE)
    @PutMapping(value = entity )
    public ResponseModel roleUpdateOne(@Valid Role role){
        return roleService.updateOne(role);
    }

    @RequiresPermissions(entity + PermissionActionConstant.DELETE)
    @DeleteMapping(value = entity+"/{roleId}")
    public ResponseModel roleDeleteOne(@PathVariable("roleId") Integer id){
        return roleService.deleteOneById(id);
    }

    @RequiresPermissions(entity+PermissionActionConstant.FIND)
    @GetMapping(value = entity+"/{roleId}/"+PermissionController.entity)
    public ResponseModel getPermissionListByRoleId(@PathVariable("roleId") Integer roleId){
        return roleService.getPermissionListByRoleId(roleId);
    }
}
