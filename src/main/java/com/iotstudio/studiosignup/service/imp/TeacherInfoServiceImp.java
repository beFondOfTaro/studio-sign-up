package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.object.entity.TeacherInfo;
import com.iotstudio.studiosignup.repository.TeacherInfoRepository;
import com.iotstudio.studiosignup.repository.UserRepository;
import com.iotstudio.studiosignup.service.TeacherInfoService;
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

@Service
@Transactional
public class TeacherInfoServiceImp implements TeacherInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherInfoServiceImp.class);

    @Autowired
    private TeacherInfoRepository teacherInfoRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseModel addOne(TeacherInfo teacherInfo) {
        return new ResponseModel(teacherInfoRepository.save(teacherInfo));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            teacherInfoRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(TeacherInfo teacherInfo) {
        return new ResponseModel(teacherInfoRepository.save(teacherInfo));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(teacherInfoRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(teacherInfoRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<TeacherInfo> teacherInfoPage = teacherInfoRepository.findAll(pageable);
        PageDataModel teacherInfoPageDataModel =
                new PageDataModel(
                        teacherInfoPage.getTotalElements(),
                        teacherInfoPage.getTotalPages(),
                        teacherInfoPage.getContent()
                );
        return new ResponseModel(teacherInfoPageDataModel);
    }

    @Override
    public ResponseModel addTeacherInfoByUserId(TeacherInfo teacherInfo, String userId) {
        String msg;
        Integer intUserId = Integer.valueOf(userId);
        //验证学生信息是否已经存在，存在直接返回失败
        if (teacherInfoRepository.findTeacherInfoByUserId(intUserId) != null){
            msg = "已存在该学生信息，请不要重复添加";
            LOGGER.warn(msg);
            return new ResponseModel(msg);
        }
        teacherInfo.setUserId(intUserId);
        return new ResponseModel(teacherInfoRepository.save(teacherInfo));
    }

    @Override
    public ResponseModel updateOneByUserIdAndTeacherId(TeacherInfo teacherInfo, String userId) {
        String msg = "提交失败";
        Integer intUserId = Integer.valueOf(userId);
        try {
            if (teacherInfoRepository.findTeacherInfoByUserId(intUserId) != null){
                msg = "不存在该老师信息!";
                LOGGER.error(msg);
                return new ResponseModel(msg);
            }
            if (teacherInfoRepository.updateTeacherInfoByUserIdAndId(
                    intUserId,
                    teacherInfo.getId(),
                    teacherInfo.getTeacherNumber())
                    == 1){
                msg = "提交成功！";
                LOGGER.info(msg);
                return new ResponseModel(true,msg);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return new ResponseModel(msg);
    }

    @Override
    public ResponseModel deleteOneByUserId(String userId,String teacherId) {
        try {
            teacherInfoRepository.deleteTeacherInfoByUserId(Integer.valueOf(userId));
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel findTeacherInfoListByUserId(String userId) {
        Integer intUserId = Integer.valueOf(userId);
        return new ResponseModel(teacherInfoRepository.findTeacherInfoByUserId(intUserId));
    }


}
