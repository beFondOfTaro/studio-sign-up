package com.iotstudio.studiosignup.service.imp;

import com.iotstudio.studiosignup.constant.HttpParamKey;
import com.iotstudio.studiosignup.converter.User2UserDtoConverter;
import com.iotstudio.studiosignup.object.entity.Role;
import com.iotstudio.studiosignup.object.entity.StudentInfo;
import com.iotstudio.studiosignup.object.entity.TeacherInfo;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.repository.*;
import com.iotstudio.studiosignup.service.UserService;
import com.iotstudio.studiosignup.shiro.token.StatelessAuthenticationToken;
import com.iotstudio.studiosignup.shiro.token.TokenUtil;
import com.iotstudio.studiosignup.util.CookieUtil;
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

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private StudentInfoRepository studentInfoRepository;
    @Autowired
    private TeacherInfoRepository teacherInfoRepository;
    @Autowired
    private TokenUtil tokenUtil;

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
    public ResponseModel selectOneById(Integer id) {
        User targetUser = userRepository.findOne(id);//查询对象
        return new ResponseModel(User2UserDtoConverter.convert(targetUser));
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
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        user.setRoleList(roleList);
        return new ResponseModel(userRepository.save(user));
    }

    @Override
    public ResponseModel login(HttpServletResponse response,String username, String password) {
        String msg;
        ResponseModel responseModel;
        User user = userRepository.findByUsername(username);
        if (user == null){
            msg = "用户不存在!";
            responseModel = new ResponseModel(false,msg,null);
        }else {
            if (user.getPassword().equals(password)){
                //创建消息摘要
                String digest = tokenUtil.createDigest(new StatelessAuthenticationToken(user.getId().toString()),user.getPassword());
                msg = "登录成功！";
                Map<String,Object> data = new HashMap<>();
                data.put("digest",digest);
                data.put("userDto",User2UserDtoConverter.convert(user));
                CookieUtil.addCookie(response, HttpParamKey.CLIENT_ID, user.getId().toString());
                CookieUtil.addCookie(response, HttpParamKey.CLIENT_DIGEST,digest);
                responseModel = new ResponseModel(true,msg,data);
            }else {
                msg = "用户名或密码错误！";
                responseModel = new ResponseModel(false,msg,null);
            }
        }
        LOGGER.info(msg);
        return responseModel;
    }

    @Override
    public ResponseModel logout(String userId) {
        String msg;
        ResponseModel responseModel;
        if (tokenUtil.getToken(userId) == null){
            msg = "用户已经注销！";
            responseModel = new ResponseModel(msg);
        }else {
            if (tokenUtil.deleteToken(Integer.valueOf(userId))){
                msg = "注销成功！";
                responseModel = new ResponseModel(true,msg,null);
            }else {
                msg = "注销失败!";
                responseModel =  new ResponseModel(msg);
            }
        }
        LOGGER.info(msg);
        return responseModel;
    }

    @Override
    public ResponseModel studentRegister(User user, StudentInfo studentInfo) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findRoleByName("学生"));
        user.setRoleList(roleList);
        User savedUser = userRepository.save(user);
        studentInfo.setUserId(savedUser.getId());
        return new ResponseModel(studentInfoRepository.save(studentInfo));
    }

    @Override
    public ResponseModel teacherRegister(User user, TeacherInfo teacherInfo) {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepository.findRoleByName("教师"));
        user.setRoleList(roleList);
        User savedUser = userRepository.save(user);
        teacherInfo.setUserId(savedUser.getId());
        return new ResponseModel(teacherInfoRepository.save(teacherInfo));
    }
}
