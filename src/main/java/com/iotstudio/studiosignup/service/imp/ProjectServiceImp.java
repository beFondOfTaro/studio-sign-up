package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.object.entity.Project;
import com.iotstudio.studiosignup.repository.ProjectRepository;
import com.iotstudio.studiosignup.repository.SighUpInfoRepository;
import com.iotstudio.studiosignup.service.ProjectService;
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
public class ProjectServiceImp implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImp.class);

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SighUpInfoRepository sighUpInfoRepository;

    @Override
    public ResponseModel addOne(Project project) {
        return new ResponseModel(projectRepository.save(project));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            Project project = new Project();
            project.setId(id);
            sighUpInfoRepository.deleteByProject(project);
            projectRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(Project project) {
        return new ResponseModel(projectRepository.save(project));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(projectRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(projectRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<Project> projectPage = projectRepository.findAll(pageable);
        PageDataModel projectPageDataModel =
                new PageDataModel(
                        projectPage.getTotalElements(),
                        projectPage.getTotalPages(),
                        projectPage.getContent()
                );
        return new ResponseModel(projectPageDataModel);
    }
}
