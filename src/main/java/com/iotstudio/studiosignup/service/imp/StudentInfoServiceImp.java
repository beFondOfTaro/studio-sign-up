package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.StudentInfo;
import com.iotstudio.studiosignup.repository.StudentInfoRepository;
import com.iotstudio.studiosignup.service.StudentInfoService;
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
public class StudentInfoServiceImp implements StudentInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentInfoServiceImp.class);

    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Override
    public ResponseModel addOne(StudentInfo studentInfo) {
        return new ResponseModel(studentInfoRepository.save(studentInfo));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            studentInfoRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(StudentInfo studentInfo) {
        return new ResponseModel(studentInfoRepository.save(studentInfo));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(studentInfoRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(studentInfoRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<StudentInfo> studentInfoPage = studentInfoRepository.findAll(pageable);
        PageDataModel studentInfoPageDataModel =
                new PageDataModel(
                        studentInfoPage.getTotalElements(),
                        studentInfoPage.getTotalPages(),
                        studentInfoPage.getContent()
                );
        return new ResponseModel(studentInfoPageDataModel);
    }
}
