package com.iotstudio.studiosignup.controller.userAccess;

import com.iotstudio.studiosignup.entity.StudentInfo;
import com.iotstudio.studiosignup.service.StudentInfoService;
import com.iotstudio.studiosignup.util.CookieUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user/{"+ CookieUtil.clientIdKey + "}")
public class UserAccessStudentInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccessStudentInfoController.class);

    private final String entity = "studentInfo";
    @Autowired
    private StudentInfoService studentInfoService;

    /**
     * 更新学生信息
     * @param studentInfo 学生信息
     * @param photoFile 照片文件
     * @param userId 用户id
     * @return 信息
     */
    @PutMapping(value = entity )
    public ResponseModel studentInfoUpdateOne(StudentInfo studentInfo,
                                     @RequestParam("file") MultipartFile photoFile,
                                     @PathVariable(CookieUtil.clientIdKey) String userId){
        return studentInfoService.updateOne(studentInfo,userId,photoFile);
    }

    @PostMapping(value = entity)
    public ResponseModel studentInfoAddOne(@Valid StudentInfo studentInfo,
                                           @PathVariable(CookieUtil.clientIdKey) String userId,
                                           @RequestParam("file") MultipartFile photoFile,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            List<String> msgList = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                String msg = error.getDefaultMessage();
                LOGGER.info(msg);
                msgList.add(msg);
            }
            return new ResponseModel(msgList.toString());
        }
        return studentInfoService.addOne(studentInfo,userId,photoFile);
    }
}
