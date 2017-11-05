package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.ProjectType;
import com.iotstudio.studiosignup.repository.ProjectTypeRepository;
import com.iotstudio.studiosignup.service.ProjectTypeService;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectTypeServiceImp implements ProjectTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectTypeServiceImp.class);

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @Override
    public ResponseModel addOne(ProjectType projectType) {
        return new ResponseModel(projectTypeRepository.save(projectType));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            projectTypeRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(HttpStatus.BAD_REQUEST.value(),ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(ProjectType projectType) {
        return new ResponseModel(projectTypeRepository.save(projectType));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(projectTypeRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(projectTypeRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        return new ResponseModel(projectTypeRepository.findAll(pageable));
    }
}
