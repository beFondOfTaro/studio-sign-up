package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.Teacher;
import com.iotstudio.studiosignup.service.TeacherService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin/{admin_id}")
public class TeacherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    private final String entity = "teacher";

//    @GetMapping(value = entity)
//    public ResponseModel teacherList(){
//        return teacherService.selectAll();
//    }

    /**
     * 分页查询所有教师
     * @param page 页码
     * @param size 每一页的数量
     */
    @GetMapping(value = entity)
    public ResponseModel teacherListByPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return teacherService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel teacherFindOneById(@PathVariable("id") Integer id){
        return teacherService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel teacherAddOne(Teacher teacher){
        return teacherService.addOne(teacher);
    }

    @PutMapping(value = entity)
    public ResponseModel teacherUpdateOne(Teacher teacher){
        return teacherService.updateOne(teacher);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel teacherDeleteOne(@PathVariable("id") Integer id){
        return teacherService.deleteOneById(id);
    }

}
