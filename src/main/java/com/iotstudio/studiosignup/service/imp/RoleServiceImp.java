package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.object.entity.Role;
import com.iotstudio.studiosignup.repository.RoleRepository;
import com.iotstudio.studiosignup.service.RoleService;
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
public class RoleServiceImp implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImp.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseModel addOne(Role role) {
        return new ResponseModel(roleRepository.save(role));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            roleRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(Role role) {
        return new ResponseModel(roleRepository.save(role));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(roleRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(roleRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<Role> rolePage = roleRepository.findAll(pageable);
        PageDataModel rolePageDataModel =
                new PageDataModel(
                        rolePage.getTotalElements(),
                        rolePage.getTotalPages(),
                        rolePage.getContent()
                );
        return new ResponseModel(rolePageDataModel);
    }

    @Override
    public ResponseModel getPermissionListByRoleId(Integer roleId) {
        return new ResponseModel(roleRepository.findOne(roleId).getPermissionList());
    }
}
