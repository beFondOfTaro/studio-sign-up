package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.Student;
import com.iotstudio.studiosignup.repository.StudentRepository;
import com.iotstudio.studiosignup.service.StudentService;
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

import java.util.List;

@Service
@Transactional
public class StudentServiceImp implements StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImp.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ResponseModel addOne(Student student) {
        return new ResponseModel(studentRepository.save(student));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            studentRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(HttpStatus.BAD_REQUEST.value(),ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(Student student) {
        return new ResponseModel(studentRepository.save(student));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(studentRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(studentRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<Student> studentPage = studentRepository.findAll(pageable);
        PageDataModel studentPageDataModel =
                new PageDataModel(
                        studentPage.getTotalElements(),
                        studentPage.getTotalPages(),
                        studentPage.getContent()
                );
        return new ResponseModel(studentPageDataModel);
    }
}
