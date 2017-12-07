package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.TeacherInfo;
import com.iotstudio.studiosignup.service.TeacherInfoService;
import com.iotstudio.studiosignup.util.BindingResultHandlerUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "admin")
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
    public ResponseModel teacherInfoListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        return teacherInfoService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{userId}")
    public ResponseModel teacherInfoFindOneByUserId(@PathVariable("userId") String userId){
        return teacherInfoService.findOneByUserId(userId);
    }

    @PostMapping(value = entity + "/{userId}")
    public ResponseModel teacherInfoAddOneByUserId(@Valid TeacherInfo teacherInfo,
                                           @PathVariable("userId") String userId,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        return teacherInfoService.addOneByUserId(teacherInfo,userId);
    }

    @PutMapping(value = entity + "/{userId}")
    public ResponseModel teacherInfoUpdateOneByUserId(@Valid TeacherInfo teacherInfo,
                                              @PathVariable("userId") String userId,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        return teacherInfoService.updateOneByUserId(teacherInfo,userId);
    }

    @DeleteMapping(value = entity + "/{userId}")
    public ResponseModel teacherInfoDeleteOne(@PathVariable("userId") String userId){
        return teacherInfoService.deleteOneByUserId(userId);
    }

}
