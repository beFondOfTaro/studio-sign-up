package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.HttpParamKey;
import com.iotstudio.studiosignup.constant.PermissionActionConstant;
import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.object.entity.Project;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.service.ProjectService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = RoleNameConstant.API_VERSION)
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    public static final String entity = "project";

//    @GetMapping(value = entity)
//    public ResponseModel projectList(){
//        return projectService.selectAll();
//    }

    /**
     * 分页查询所有项目
     * @param page 页码
     * @param size 每一页的数量
     */
    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity)
    public ResponseModel projectListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                           @RequestParam(value = "size",defaultValue = "10") Integer size){
        return projectService.selectAllByPage(page-1,size);
    }

    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = UserController.entity + "/{userId}/" + entity+"/{projectId}")
    public ResponseModel projectFindOneById(@PathVariable("projectId") Integer id){
        return projectService.selectOneById(id);
    }

    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel findProjectsByUserId(@PathVariable("userId") Integer userId){
        return projectService.findProjectsByUserId(userId);
    }

    @RequiresPermissions(entity + PermissionActionConstant.ADD)
    @PostMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel projectAddOne(Project project,@PathVariable("userId")Integer userId){
        User user = new User();
        user.setId(userId);
        project.setUser(user);
        return projectService.addOne(project);
    }

    @RequiresPermissions(entity + PermissionActionConstant.UPDATE)
    @PutMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel projectUpdateOne(Project project){
        return projectService.updateOne(project);
    }

    @RequiresPermissions(entity + PermissionActionConstant.DELETE)
    @DeleteMapping(value = UserController.entity + "/{userId}/" + entity+"/{projectId}")
    public ResponseModel projectDeleteOne(@PathVariable("id") Integer id){
        return projectService.deleteOneById(id);
    }

}
