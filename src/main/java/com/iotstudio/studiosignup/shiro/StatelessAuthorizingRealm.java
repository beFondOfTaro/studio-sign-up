package com.iotstudio.studiosignup.shiro;

import com.iotstudio.studiosignup.shiro.token.TokenUtil;
import com.iotstudio.studiosignup.util.HmacSHA256Utils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StatelessAuthorizingRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatelessAuthorizingRealm.class);
    @Autowired
    private TokenUtil tokenUtil;
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

        //这里模拟admin帐号才有role权限
        if (userId.equals("1")){
            authorizationInfo.addRole("admin");
        }
        return  authorizationInfo;
    }

}
