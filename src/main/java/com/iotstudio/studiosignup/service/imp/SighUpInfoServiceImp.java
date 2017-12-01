package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.Project;
import com.iotstudio.studiosignup.entity.SighUpInfo;
import com.iotstudio.studiosignup.entity.User;
import com.iotstudio.studiosignup.repository.ProjectRepository;
import com.iotstudio.studiosignup.repository.SighUpInfoRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return new ResponseModel(HttpStatus.BAD_REQUEST.value(),ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(SighUpInfo sighUpInfo) {
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel updateOne(SighUpInfo sighUpInfo, Integer userId, String projectName) {
        return addOne(sighUpInfo,userId,projectName);
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
            return new ResponseModel(HttpStatus.BAD_REQUEST.value(),msg,null);
        }
        sighUpInfo.setUser(user);
        Project project = projectRepository.findProjectByName(projectName);
        sighUpInfo.setProject(project);
        return new ResponseModel(sighUpInfoRepository.save(sighUpInfo));
    }

    @Override
    public ResponseModel updateOne(SighUpInfo sighUpInfo, String username, String projectName) {
        return addOne(sighUpInfo,username,projectName);
    }
}
