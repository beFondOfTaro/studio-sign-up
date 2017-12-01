package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.Project;
import com.iotstudio.studiosignup.entity.User;
import com.iotstudio.studiosignup.service.ProjectService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin/{admin_id}")
public class ProjectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    private final String entity = "project";

//    @GetMapping(value = entity)
//    public ResponseModel projectList(){
//        return projectService.selectAll();
//    }

    /**
     * 分页查询所有项目
     * @param page 页码
     * @param size 每一页的数量
     */
    @GetMapping(value = entity)
    public ResponseModel projectListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                           @RequestParam(value = "size",defaultValue = "10") Integer size){
        return projectService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel projectFindOneById(@PathVariable("id") Integer id){
        return projectService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel projectAddOne(Project project,@PathVariable("admin_id")Integer adminId){
        User user = new User();
        user.setId(adminId);
        project.setUser(user);
        return projectService.addOne(project);
    }

    @PutMapping(value = entity)
    public ResponseModel projectUpdateOne(Project project){
        return projectService.updateOne(project);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel projectDeleteOne(@PathVariable("id") Integer id){
        return projectService.deleteOneById(id);
    }

}
