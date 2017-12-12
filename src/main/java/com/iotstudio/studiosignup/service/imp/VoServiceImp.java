package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.object.entity.TeacherInfo;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.object.vo.UserStudentInfoVo;
import com.iotstudio.studiosignup.object.vo.UserTeacherVo;
import com.iotstudio.studiosignup.repository.StudentInfoRepository;
import com.iotstudio.studiosignup.repository.TeacherInfoRepository;
import com.iotstudio.studiosignup.repository.UserRepository;
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
}
