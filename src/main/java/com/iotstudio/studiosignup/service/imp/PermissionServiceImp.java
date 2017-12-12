package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.object.entity.Permission;
import com.iotstudio.studiosignup.repository.PermissionRepository;
import com.iotstudio.studiosignup.service.PermissionService;
import com.iotstudio.studiosignup.util.model.PageDataModel;
import com.iotstudio.studiosignup.util.model.ResponseModel;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j
public class PermissionServiceImp implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public ResponseModel addOne(Permission permission) {
        return new ResponseModel(permissionRepository.save(permission));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            permissionRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(Permission permission) {
        return new ResponseModel(permissionRepository.save(permission));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(permissionRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(permissionRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"name","ownerAvailable");
        //根据分页请求查询所有实体
        Page<Permission> permissionPage = permissionRepository.findAll(pageable);
        PageDataModel permissionPageDataModel =
                new PageDataModel(
                        permissionPage.getTotalElements(),
                        permissionPage.getTotalPages(),
                        permissionPage.getContent()
                );
        return new ResponseModel(permissionPageDataModel);
    }
}
