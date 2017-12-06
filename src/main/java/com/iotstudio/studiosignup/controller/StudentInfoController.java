package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.StudentInfo;
import com.iotstudio.studiosignup.service.StudentInfoService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin/{admin_id}")
public class StudentInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentInfoController.class);

    @Autowired
    private StudentInfoService studentInfoService;

    private final String entity = "studentInfo";

//    @GetMapping(value = entity)
//    public ResponseModel studentInfoList(){
//        return studentInfoService.selectAll();
//    }

    /**
     * 分页查询所有学生信息
     * @param page 页码
     * @param size 每一页的数量
     */
//    @ApiOperation(value = "分页查询所有学生信息",notes = "//")
//    @ApiImplicitParams({
//            @ApiImplicitParam(dataType = "java.lang.Integer", name = "admin_id", value = "管理员id", required = true, paramType = "path"),
//            @ApiImplicitParam(dataType = "Integer", name = "page", value = "页码"),
//            @ApiImplicitParam(dataType = "Integer", name = "size", value = "每一页的数量")
//    })
    @GetMapping(value = entity)
    public ResponseModel studentInfoListByPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return studentInfoService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel studentInfoFindOneById(@PathVariable("id") Integer id){
        return studentInfoService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel studentInfoAddOne(StudentInfo studentInfo){
        return studentInfoService.addOne(studentInfo);
    }

    @PutMapping(value = entity)
    public ResponseModel studentInfoUpdateOne(StudentInfo studentInfo){
        return studentInfoService.updateOne(studentInfo);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel studentInfoDeleteOne(@PathVariable("id") Integer id){
        return studentInfoService.deleteOneById(id);
    }

}
