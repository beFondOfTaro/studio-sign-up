package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.converter.SighUpInfoVoConverter;
import com.iotstudio.studiosignup.object.entity.*;
import com.iotstudio.studiosignup.object.vo.SighUpInfoVo;
import com.iotstudio.studiosignup.object.vo.UserStudentInfoVo;
import com.iotstudio.studiosignup.object.vo.UserTeacherVo;
import com.iotstudio.studiosignup.repository.*;
import com.iotstudio.studiosignup.service.VoService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class VoServiceImp implements VoService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private TeacherInfoRepository teacherInfoRepository;
    @Autowired
    private SighUpInfoRepository sighUpInfoRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResponseModel getUserStudentInfo(Integer userId) {
        User user = userRepository.findOne(userId);
        StudentInfo studentInfo = studentInfoRepository.findByUserId(userId);
        UserStudentInfoVo vo = new UserStudentInfoVo();
        if (studentInfo!=null){
            vo.setMajor(studentInfo.getMajor());
            vo.setPhoto(studentInfo.getPhoto());
            vo.setQqNumber(studentInfo.getQqNumber());
            vo.setStudentNumber(studentInfo.getStudentNumber());
            vo.setStudentInfoId(studentInfo.getId());
        }
        vo.setId(userId);
        vo.setPhone(user.getPhone());
        vo.setRealName(user.getRealName());
        vo.setUsername(user.getUsername());
        return new ResponseModel(vo);
    }

    @Override
    public ResponseModel getUserTeacherInfo(Integer userId) {
        User user = userRepository.findOne(userId);
        TeacherInfo teacherInfo = teacherInfoRepository.findTeacherInfoByUserId(userId);
        UserTeacherVo vo = new UserTeacherVo();
        if (teacherInfo != null){
            vo.setTeacherNumber(teacherInfo.getTeacherNumber());
        }
        vo.setUserId(userId);
        vo.setUsername(user.getUsername());
        vo.setRealName(user.getRealName());
        vo.setPhone(user.getPhone());
        return new ResponseModel(vo);
    }

    @Override
    public ResponseModel getUserSighUpInfo(Integer sighUpInfoId) {
        SighUpInfo sighUpInfo = sighUpInfoRepository.findOne(sighUpInfoId);
        User user = sighUpInfo.getUser();
        StudentInfo studentInfo = studentInfoRepository.findByUserId(user.getId());
        return new ResponseModel(SighUpInfoVoConverter.convert(sighUpInfo,studentInfo,user));
    }

    @Override
    public ResponseModel sighUp(SighUpInfo sighUpInfo,Integer userId, String projectName) {
        String msg;
        SighUpInfo savingInfo = new SighUpInfo();
        User user = new User();
        user.setId(userId);
        Project project = projectRepository.findProjectByName(projectName);
        if (project == null){
            msg = "不存在该项目";
            return new ResponseModel(false,msg);
        }
        savingInfo.setId(sighUpInfo.getId());
        savingInfo.setUser(user);
        savingInfo.setProject(project);
        savingInfo.setPersonalIntroduction(sighUpInfo.getPersonalIntroduction());
        return new ResponseModel(sighUpInfoRepository.save(savingInfo));
    }
}
