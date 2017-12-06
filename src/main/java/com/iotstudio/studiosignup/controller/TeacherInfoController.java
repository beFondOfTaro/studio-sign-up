package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.TeacherInfo;
import com.iotstudio.studiosignup.service.TeacherInfoService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin/{admin_id}")
public class TeacherInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherInfoController.class);

    @Autowired
    private TeacherInfoService teacherInfoService;

    private final String entity = "teacherInfo";

//    @GetMapping(value = entity)
//    public ResponseModel teacherInfoList(){
//        return teacherInfoService.selectAll();
//    }

    /**
     * 分页查询所有教师信息
     * @param page 页码
     * @param size 每一页的数量
     */
    @GetMapping(value = entity)
    public ResponseModel teacherInfoListByPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return teacherInfoService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel teacherInfoFindOneById(@PathVariable("id") Integer id){
        return teacherInfoService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel teacherInfoAddOne(TeacherInfo teacherInfo){
        return teacherInfoService.addOne(teacherInfo);
    }

    @PutMapping(value = entity)
    public ResponseModel teacherInfoUpdateOne(TeacherInfo teacherInfo){
        return teacherInfoService.updateOne(teacherInfo);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel teacherInfoDeleteOne(@PathVariable("id") Integer id){
        return teacherInfoService.deleteOneById(id);
    }

}
