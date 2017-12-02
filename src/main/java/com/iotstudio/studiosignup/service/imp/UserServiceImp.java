package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.entity.Role;
import com.iotstudio.studiosignup.entity.User;
import com.iotstudio.studiosignup.repository.RoleRepository;
import com.iotstudio.studiosignup.repository.SighUpInfoRepository;
import com.iotstudio.studiosignup.repository.UserRepository;
import com.iotstudio.studiosignup.service.UserService;
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
public class UserServiceImp implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SighUpInfoRepository sighUpInfoRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseModel addOne(User user) {
        return new ResponseModel(userRepository.save(user));
    }

    @Override
    public ResponseModel deleteOneById(Integer id) {
        try {
            User user = new User();
            user.setId(id);
            sighUpInfoRepository.deleteByUser(user);
            userRepository.delete(id);
        }
        catch (Exception e){
            return new ResponseModel(false,ResponseModel.FAILED_MSG,e.getMessage());
        }
        return new ResponseModel();
    }

    @Override
    public ResponseModel updateOne(User user) {
        return new ResponseModel(userRepository.save(user));
    }

    @Override
    public ResponseModel updateOne(User user,String roleName) {
        Role role = roleRepository.findRoleByName(roleName);
        user.setRole(role);
        return new ResponseModel(userRepository.save(user));
    }

    @Override
    public ResponseModel selectOneById(Integer id) {
        return new ResponseModel(userRepository.findOne(id));
    }

    @Override
    public ResponseModel selectAll() {
        return new ResponseModel(userRepository.findAll());
    }

    @Override
    public ResponseModel selectAllByPage(Integer page,Integer size) {
        //建立分页请求，返回一个Pageable对象
        Pageable pageable = new PageRequest(page,size, Sort.Direction.ASC,"id");
        //根据分页请求查询所有实体
        Page<User> userPage = userRepository.findAll(pageable);
        PageDataModel userPageDataModel =
                new PageDataModel(
                        userPage.getTotalElements(),
                        userPage.getTotalPages(),
                        userPage.getContent()
                );
        return new ResponseModel(userPageDataModel);
    }

    @Override
    public ResponseModel addOne(User user,String roleName) {
        String username = user.getUsername();
        User existedUser = userRepository.findByUsername(username);
        if (existedUser != null){
            String msg = "用户名"+ username +"已经存在！";
            LOGGER.warn(msg);
            return new ResponseModel(false,msg,null);
        }
        Role role = roleRepository.findRoleByName(roleName);
        user.setRole(role);
        return new ResponseModel(userRepository.save(user));
    }
}
