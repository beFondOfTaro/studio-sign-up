package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.Teacher;
import com.iotstudio.studiosignup.repository.TeacherRepository;
import com.iotstudio.studiosignup.service.TeacherService;
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
public class TeacherServiceImp implements TeacherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImp.class);

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public ResponseModel addOne(Teacher teacher) {
        return new ResponseModel(teacherRepository.save(teacher));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            teacherRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(HttpStatus.BAD_REQUEST.value(),ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(Teacher teacher) {
        return new ResponseModel(teacherRepository.save(teacher));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(teacherRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(teacherRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);
        PageDataModel teacherPageDataModel =
                new PageDataModel(
                        teacherPage.getTotalElements(),
                        teacherPage.getTotalPages(),
                        teacherPage.getContent()
                );
        return new ResponseModel(teacherPageDataModel);
    }
}
