package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.object.vo.UserStudentInfoVo;
import com.iotstudio.studiosignup.repository.StudentInfoRepository;
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

    @Override
    public ResponseModel getUserStudentInfo(Integer userId) {
        User user = userRepository.findOne(userId);
        StudentInfo studentInfo = studentInfoRepository.findByUserId(userId);
        UserStudentInfoVo vo = new UserStudentInfoVo();
        vo.setId(userId);
        vo.setMajor(studentInfo.getMajor());
        vo.setPhone(user.getPhone());
        vo.setPhoto(studentInfo.getPhoto());
        vo.setQqNumber(studentInfo.getQqNumber());
        vo.setRealName(user.getRealName());
        vo.setUsername(user.getUsername());
        vo.setStudentNumber(studentInfo.getStudentNumber());
        return new ResponseModel(vo);
    }
}
