package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.TeacherInfo;
import com.iotstudio.studiosignup.repository.TeacherInfoRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherInfoServiceImp implements TeacherInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherInfoServiceImp.class);

    @Autowired
    private TeacherInfoRepository teacherInfoRepository;

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
            return new ResponseModel(HttpStatus.BAD_REQUEST.value(),ResponseModel.FAILED_MSG,e.getMessage());
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
}
