package com.iotstudio.studiosignup.shiro;

import com.iotstudio.studiosignup.object.entity.Permission;
import com.iotstudio.studiosignup.object.entity.Role;
import com.iotstudio.studiosignup.object.entity.User;
import com.iotstudio.studiosignup.repository.PermissionRepository;
import com.iotstudio.studiosignup.repository.UserRepository;
import com.iotstudio.studiosignup.shiro.token.StatelessAuthenticationToken;
import com.iotstudio.studiosignup.shiro.token.TokenUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatelessAuthorizingRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatelessAuthorizingRealm.class);
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    private boolean isOwner;
    /**
     * 仅支持StatelessToken 类型的Token，
     * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
     * Please ensure that the appropriate Realm implementation is configured correctly or
     * that the realm accepts AuthenticationTokens of this type.StatelessAuthcFilter.isAccessAllowed()
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessAuthenticationToken;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("StatelessRealm.doGetAuthenticationInfo()");
        StatelessAuthenticationToken token = (StatelessAuthenticationToken)authenticationToken;
        String userId = (String)token.getPrincipal();//不能为null，否则会报错
        isOwner = Boolean.getBoolean(token.getParams().get("isOwner"));//是否为用户私有资源
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return tokenUtil.validTokenBySimpleAuthenticationInfo(userId,getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOGGER.info("StatelessRealm.doGetAuthorizationInfo()");
        //根据用户名查找角色，请根据需求实现
        String userId = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //查询user的角色并添加权限
        User user = userRepository.findOne(Integer.valueOf(userId));
        for (Role role : user.getRoleList()){
            authorizationInfo.addRole(role.getName());
            for (Permission permission : role.getPermissionList()){
                authorizationInfo.addStringPermission(permission.getName());
            }
        }
        //私有资源授权
        List<Permission> permissionList = permissionRepository.findAll();
        if (isOwner){
            for (Permission permission : permissionList){
                if (permission.isOwnerAvailable()){
                    authorizationInfo.addStringPermission(permission.getName());
                }
            }
        }
        return  authorizationInfo;
    }

}
