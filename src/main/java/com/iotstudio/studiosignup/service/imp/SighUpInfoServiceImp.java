package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.converter.SighUpInfoVoConverter;
import com.iotstudio.studiosignup.object.entity.Project;
import com.iotstudio.studiosignup.object.entity.SighUpInfo;
import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.object.vo.SighUpInfoVo;
import com.iotstudio.studiosignup.repository.ProjectRepository;
import com.iotstudio.studiosignup.repository.SighUpInfoRepository;
import com.iotstudio.studiosignup.repository.StudentInfoRepository;
import com.iotstudio.studiosignup.repository.UserRepository;
import com.iotstudio.studiosignup.service.SighUpInfoService;
import com.iotstudio.studiosignup.util.model.PageDataModel;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SighUpInfoServiceImp implements SighUpInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SighUpInfoServiceImp.class);

    @Autowired
    private SighUpInfoRepository sighUpInfoRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Override
    public ResponseModel addOne(SighUpInfo sighUpInfo) {
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            sighUpInfoRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(SighUpInfo sighUpInfo) {
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(sighUpInfoRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(sighUpInfoRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<SighUpInfo> sighUpInfoPage = sighUpInfoRepository.findAll(pageable);
        PageDataModel sighUpInfoPageDataModel =
                new PageDataModel(
                        sighUpInfoPage.getTotalElements(),
                        sighUpInfoPage.getTotalPages(),
                        sighUpInfoPage.getContent()
                );
        return new ResponseModel(sighUpInfoPageDataModel);
    }

    @Override
    public ResponseModel addOne(SighUpInfo sighUpInfo, Integer userId, String projectName) {
        User user = new User();
        user.setId(userId);
        sighUpInfo.setUser(user);
        Project project = projectRepository.findProjectByName(projectName);
        sighUpInfo.setProject(project);
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel addOne(SighUpInfo sighUpInfo, String username, String projectName) {
        User user = userRepository.findByUsername(username);
        if (user == null){
            String msg = "用户"+ username +"不存在！";
            LOGGER.warn(msg);
            return new ResponseModel(false,msg,null);
        }
        sighUpInfo.setUser(user);
        Project project = projectRepository.findProjectByName(projectName);
        sighUpInfo.setProject(project);
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel updateOne(SighUpInfo sighUpInfo,Integer userId,Integer projectId,Integer sighUpInfoId) {
        User user = new User();
        Project project = new Project();
        user.setId(userId);
        project.setId(projectId);
        sighUpInfo.setUser(user);
        sighUpInfo.setProject(project);
        sighUpInfo.setId(sighUpInfoId);
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    public ResponseModel updateCheckCodeByUserIdAndProjectIdAndSighUpInfoId(
            Integer checkCode, Integer userId, Integer projectId,Integer sighUpInfoId
    ){
        if (sighUpInfoRepository.updateByUserIdAndProjectIdAndId(checkCode,userId,projectId,sighUpInfoId) == 1){
            return new ResponseModel();
        }
        return new ResponseModel("更新审核状态失败！");
    }

    @Override
    public ResponseModel updateCheckCodeByUserIdAndSighUpInfoId(Integer checkCode, Integer userId, Integer sighUpInfoId) {
        if (sighUpInfoRepository.updateByUserIdAndId(checkCode,userId,sighUpInfoId) == 1){
            return new ResponseModel();
        }
        return new ResponseModel("更新审核状态失败！");
    }

    @Override
    public ResponseModel selectSighUpInfoByUserIdAndProjectId(Integer userId, Integer projectId, HttpServletResponse response) {
        User user = new User();
        user.setId(userId);
        Project project = new Project();
        project.setId(projectId);
        return new ResponseModel(sighUpInfoRepository.findSighUpInfoListByUserAndProject(user,project));
    }

    @Override
    public ResponseModel findSighUpInfosByProjectId(Integer projectId) {
        Project project = new Project();
        project.setId(projectId);
        List<SighUpInfo> sighUpInfoList = sighUpInfoRepository.findSighUpInfoListByProject(project);
        List<SighUpInfoVo> sighUpInfoVoList = new ArrayList<>();
        for (SighUpInfo sighUpInfo : sighUpInfoList){
            User user = sighUpInfo.getUser();
            StudentInfo studentInfo = studentInfoRepository.findByUserId(user.getId());
            sighUpInfoVoList.add(SighUpInfoVoConverter.convert(sighUpInfo,studentInfo,user));
        }
        return new ResponseModel(sighUpInfoVoList);
    }

    @Override
    public ResponseModel findSighUpInfosByUserId(Integer userId) {
        User user = new User();
        user.setId(userId);
        List<SighUpInfo> sighUpInfoList = sighUpInfoRepository.findAllByUser(user);
        List<SighUpInfoVo> sighUpInfoVoList = new ArrayList<>();
        for (SighUpInfo sighUpInfo : sighUpInfoList){
            User gotUser = sighUpInfo.getUser();
            StudentInfo studentInfo = studentInfoRepository.findByUserId(gotUser.getId());
            sighUpInfoVoList.add(SighUpInfoVoConverter.convert(sighUpInfo,studentInfo,gotUser));
        }
        return new ResponseModel(sighUpInfoVoList);
    }
}
