package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.constant.PermissionActionConstant;
import com.iotstudio.studiosignup.object.entity.SighUpInfo;
import com.iotstudio.studiosignup.service.SighUpInfoService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SighUpInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SighUpInfoController.class);

    @Autowired
    private SighUpInfoService sighUpInfoService;

    public static final String entity = "sighUpInfo";

//    @GetMapping(value = entity)
//    public ResponseModel sighUpInfoList(){
//        return sighUpInfoService.selectAll();
//    }

    /**
     * 分页查询所有报名信息
     * @param page 页码
     * @param size 每一页的数量
     */
    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = entity)
    public ResponseModel sighUpInfoListByPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return sighUpInfoService.selectAllByPage(page-1,size);
    }

    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = UserController.entity + "/{userId}/" + entity+"/{sighUpInfoId}")
    public ResponseModel sighUpInfoFindOneByUserId(@PathVariable("userId") Integer userId,
                                               @PathVariable("sighUpInfoId") Integer sighUpInfoId,
                                               HttpServletResponse response){
        return sighUpInfoService.selectSighUpInfoByUserIdAndProjectId(userId,sighUpInfoId,response);
    }

    /**
     * 查询某一个项目下的所有报名信息
     * @return 某一个项目下的所有报名信息
     */
    @RequiresPermissions(entity + PermissionActionConstant.FIND)
    @GetMapping(value = ProjectController.entity + "/{projectId}/" + entity)
    public ResponseModel findSighUpInfoByProjectId(@PathVariable("userId") Integer projectId){
        return sighUpInfoService.findSighUpInfoByProjectId(projectId);
    }

    @RequiresPermissions(entity + PermissionActionConstant.ADD)
    @PostMapping(value = UserController.entity + "/{userId}/" + entity)
    public ResponseModel sighUpInfoAddOne(SighUpInfo sighUpInfo,
                                          @RequestParam("username")String username,
                                          @RequestParam("projectName")String projectName){
        return sighUpInfoService.addOne(sighUpInfo,username,projectName);
    }

    @RequiresPermissions(entity + PermissionActionConstant.UPDATE)
    @PutMapping(value = UserController.entity + "/{userId}/" + ProjectController.entity + "{/projectId}/" + entity + "/{sighUpInfoId}")
    public ResponseModel sighUpInfoUpdateOne(SighUpInfo sighUpInfo,
                                             @PathVariable("sighUpInfoId") Integer sighUpInfoId,
                                             @PathVariable("userId")Integer userId,
                                             @PathVariable("projectId")Integer projectId){
        return sighUpInfoService.updateOne(sighUpInfo,userId,projectId,sighUpInfoId);
    }

    @RequiresPermissions(value = entity + PermissionActionConstant.UPDATE)
    @PatchMapping(value = UserController.entity + "/{userId}/" + ProjectController.entity + "/{projectId}/" + entity + "/{sighUpInfoId}")
    public ResponseModel updateCheckCodeByUserIdAndProjectIdAndSighUpInfoId(@RequestParam("checkCode") Integer checkCode,
                                                             @PathVariable("userId")Integer userId,
                                                             @PathVariable("projectId")Integer projectId,
                                                             @PathVariable("sighUpInfoId") Integer sighUpInfoId){
        return sighUpInfoService.updateCheckCodeByUserIdAndProjectIdAndSighUpInfoId(checkCode,userId,projectId,sighUpInfoId);
    }

    @RequiresPermissions(entity + PermissionActionConstant.DELETE)
    @DeleteMapping(value = UserController.entity + "/{userId}/" + entity+"/{sighUpInfoId}")
    public ResponseModel sighUpInfoDeleteOne(@PathVariable("id") Integer id){
        return sighUpInfoService.deleteOneById(id);
    }

}
