package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.PermissionActionConstant;
import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.object.entity.TeacherInfo;
import com.iotstudio.studiosignup.service.TeacherInfoService;
import com.iotstudio.studiosignup.util.BindingResultHandlerUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = RoleNameConstant.API_VERSION)
public class TeacherInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherInfoController.class);

    @Autowired
    private TeacherInfoService teacherInfoService;

    public static final String entity = "teacherInfo";

//    @GetMapping(value = entity)
//    public ResponseModel teacherInfoList(){
//        return teacherInfoService.selectAll();
//    }

    /**
     * 分页查询所有教师信息
     * @param page 页码
     * @param size 每一页的数量
     */
    @RequiresPermissions(value = entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity)
    public ResponseModel teacherInfoListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        return teacherInfoService.selectAllByPage(page-1,size);
    }

    /**
     * 根据用户id查询所有教师信息
     * @param userId
     * @return
     */
    @RequiresPermissions(value = entity + PermissionActionConstant.ADD)
    @GetMapping(value = UserController.entity + "/{userId}" + entity)
    public ResponseModel findTeacherInfoListByUserId(@PathVariable("userId") String userId){
        return teacherInfoService.findTeacherInfoListByUserId(userId);
    }

    /**
     * 根据用户id增加一条教师信息
     * @param teacherInfo
     * @param userId
     * @param bindingResult
     * @return
     */
    @RequiresPermissions(value = entity + PermissionActionConstant.ADD)
    @PostMapping(value = UserController.entity + "/{userId}/" + entity )
    public ResponseModel teacherInfoAddOneByUserId(@Valid TeacherInfo teacherInfo,
                                           @PathVariable("userId") String userId,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        return teacherInfoService.addTeacherInfoByUserId(teacherInfo,userId);
    }

    /**
     * 根据教师信息id更新该用户的一条教师信息
     * @param teacherInfo
     * @param userId
     * @param bindingResult
     * @return
     */
    @RequiresPermissions(value = entity + PermissionActionConstant.UPDATE)
    @PutMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel teacherInfoUpdateOneByUserId(@Valid TeacherInfo teacherInfo,
                                              @PathVariable("userId") String userId,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        return teacherInfoService.updateOneByUserIdAndTeacherId(teacherInfo,userId);
    }

    /**
     * 根据用户id删除一条教师信息
     * @param userId
     * @return
     */
    @RequiresPermissions(value = entity + PermissionActionConstant.DELETE)
    @DeleteMapping(value = UserController.entity + "/{userId}/" + entity + "/{teacherInfoId}")
    public ResponseModel teacherInfoDeleteOne(@PathVariable("userId") String userId,
                                              @PathVariable("teacherInfo") String teacherId){
        return teacherInfoService.deleteOneByUserId(userId,teacherId);
    }

}
