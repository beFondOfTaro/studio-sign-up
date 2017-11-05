package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.Student;
import com.iotstudio.studiosignup.service.StudentService;
import com.iotstudio.studiosignup.util.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin"+"/{admin_id}")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    private final String entity = "student";

//    @GetMapping(value = entity)
//    public ResponseModel studentList(){
//        return studentService.selectAll();
//    }

    @GetMapping(value = entity)
    public ResponseModel studentListByPage(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit){
        return studentService.selectAllByPage(page,limit);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel studentFindOneById(@PathVariable("id") Integer id){
        return studentService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel studentAddOne(Student student){
        return studentService.addOne(student);
    }

    @PutMapping(value = entity)
    public ResponseModel studentUpdateOne(Student student){
        return studentService.updateOne(student);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel studentDeleteOne(@PathVariable("id") Integer id){
        return studentService.deleteOneById(id);
    }

}
