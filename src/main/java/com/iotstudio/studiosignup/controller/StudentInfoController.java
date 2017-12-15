package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.HttpParamKey;
import com.iotstudio.studiosignup.constant.PermissionActionConstant;
import com.iotstudio.studiosignup.constant.RoleNameConstant;
import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.service.StudentInfoService;
import com.iotstudio.studiosignup.util.BindingResultHandlerUtil;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = RoleNameConstant.API_VERSION)
public class StudentInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentInfoController.class);

    @Autowired
    private StudentInfoService studentInfoService;

    public static final String entity = "studentInfo";

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
    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity)
    public ResponseModel studentInfoListByPage(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        return studentInfoService.selectAllByPage(page-1,size);
    }

    /**
     * 根据学生id查询一个学生信息
     * @param userId 学生id
     * @return 一个学生信息
     */
    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel studentInfoFindOneById(@PathVariable("userId") String userId){
        return studentInfoService.findOneByUserId(userId);
    }

    /**
     * 新增一个学生的信息
     * @param studentInfo 学生的信息
     * @param userId 用户id
     * @param photoFile 照片文件
     * @return 新增的学生信息
     */
    @RequiresPermissions(entity + PermissionActionConstant.ADD)
    @PostMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel studentInfoAddOne(@Valid StudentInfo studentInfo,
                                           @RequestHeader(HttpParamKey.CLIENT_ID) String userId,
                                           @RequestParam("file") MultipartFile photoFile,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return BindingResultHandlerUtil.onError(bindingResult);
        }
        return studentInfoService.addOne(studentInfo,userId,photoFile);
    }

    /**
     * 更新学生信息
     * @param studentInfo 学生信息
     * @param photoFile 照片文件
     * @param userId 用户id
     * @return 信息
     */
    @RequiresPermissions(entity + PermissionActionConstant.UPDATE)
    @PutMapping(value = UserController.entity + "/{userId}/" + entity + "/{id}")
    public ResponseModel studentInfoUpdateOneByUserId(@Valid StudentInfo studentInfo,
                                                      @RequestParam("file") MultipartFile photoFile,
                                                      @PathVariable("userId") String userId,
                                                      @PathVariable("id") Integer id,
                                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            BindingResultHandlerUtil.onError(bindingResult);
        }
        studentInfo.setId(id);
        return studentInfoService.updateOneByUserId(studentInfo,userId,photoFile);
    }

    /**
     * 根据学生id删除学生信息
     * @param userId 学生id
     * @return 一个学生信息
     */
    @RequiresPermissions(entity + PermissionActionConstant.DELETE)
    @DeleteMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel deleteOneByUserId(@PathVariable("userId") String userId){
        return studentInfoService.deleteOneByUserId(userId);
    }
}
