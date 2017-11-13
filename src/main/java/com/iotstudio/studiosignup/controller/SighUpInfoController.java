package com.iotstudio.studiosignup.controller;

import com.iotstudio.studiosignup.entity.SighUpInfo;
import com.iotstudio.studiosignup.service.SighUpInfoService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin/{admin_id}")
public class SighUpInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SighUpInfoController.class);

    @Autowired
    private SighUpInfoService sighUpInfoService;

    private final String entity = "sighupinfo";

//    @GetMapping(value = entity)
//    public ResponseModel sighUpInfoList(){
//        return sighUpInfoService.selectAll();
//    }

    /**
     * 分页查询所有报名信息
     * @param page 页码
     * @param size 每一页的数量
     */
    @GetMapping(value = entity)
    public ResponseModel sighUpInfoListByPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        return sighUpInfoService.selectAllByPage(page-1,size);
    }

    @GetMapping(value = entity+"/{id}")
    public ResponseModel sighUpInfoFindOneById(@PathVariable("id") Integer id){
        return sighUpInfoService.selectOneById(id);
    }

    @PostMapping(value = entity)
    public ResponseModel sighUpInfoAddOne(SighUpInfo sighUpInfo){
        return sighUpInfoService.addOne(sighUpInfo);
    }

    @PutMapping(value = entity)
    public ResponseModel sighUpInfoUpdateOne(SighUpInfo sighUpInfo){
        return sighUpInfoService.updateOne(sighUpInfo);
    }

    @DeleteMapping(value = entity+"/{id}")
    public ResponseModel sighUpInfoDeleteOne(@PathVariable("id") Integer id){
        return sighUpInfoService.deleteOneById(id);
    }

}
