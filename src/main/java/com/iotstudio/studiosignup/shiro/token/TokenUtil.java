package com.iotstudio.studiosignup.shiro.token;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.iotstudio.studiosignup.util.HmacSHA256Utils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TokenUtil {

    public static final long DEFAULT_EXPIRATION_TIME = 7;

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 创建消息摘要
     * 默认过期时间为7天
     *
     * @param token 用于生成digest的模型实体
     * @return
     */
    public String createDigest(StatelessAuthenticationToken token,String password) {
        return createDigest(token,password,DEFAULT_EXPIRATION_TIME);
    }

    public String createDigest(StatelessAuthenticationToken token,String password, long expirationTime) {
        logger.info("--------创建digest消息摘要---------");
        //进行消息摘要
        String digest = HmacSHA256Utils.digest(password, token.getParams());
        //将token存储到redis并设置过期时间
        redisTemplate.boundValueOps(token.getUserId()).set(digest, expirationTime, TimeUnit.DAYS);
        logger.info("用户id:" + token.getUserId() + ",消息摘要已创建：" + digest);
        return digest;
    }

    public String getToken(String userId){
        return redisTemplate.boundValueOps(userId).get();
    }

    /**
     * 验证token
     * 验证成功后默认重置token保存时间为7天
     *
     * @return
     */
    public boolean validToken(StatelessAuthenticationToken clientToken) {
        return validToken(clientToken, DEFAULT_EXPIRATION_TIME);
    }

    /**
     * 验证token
     *
     * @param expirationTime 重置token的保存时间(天)
     * @return
     */
    public boolean validToken(StatelessAuthenticationToken clientToken, long expirationTime) {
        boolean flag = false;
        String userIdInfo;
        if (clientToken != null) {
            userIdInfo = "用户id:" + clientToken.getUserId();
            try {
                //判断token是否存在
                Object serverToken = redisTemplate.boundValueOps(clientToken.getUserId()).get();
                if (serverToken == null || !serverToken.equals(clientToken.getClientDigest())) {
                    logger.info(userIdInfo + ",与服务端token匹配失败");
                } else {
                    flag = true;
                    redisTemplate.boundValueOps(clientToken.getUserId()).expire(expirationTime, TimeUnit.DAYS);
                    logger.info(userIdInfo + ",token验证通过，重置保存时间为" + expirationTime + "天");
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("请求参数token为空，验证失败");
        }
        return flag;
    }

    public boolean deleteToken(Integer userId) {
        try {
            redisTemplate.delete(userId.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过Shiro的SimpleAuthenticationInfo类来进行身份验证,
     * 验证成功后默认重置token保存时间为7天
     * @param userId
     * @param realmName
     * @param expirationTime
     * @return
     */
    public SimpleAuthenticationInfo validTokenBySimpleAuthenticationInfo(String userId, String realmName, long expirationTime) {
        String digest = redisTemplate.boundValueOps(userId).get();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userId, digest, realmName);
        redisTemplate.boundValueOps(userId).expire(expirationTime, TimeUnit.DAYS);
        return authenticationInfo;
    }

    public SimpleAuthenticationInfo validTokenBySimpleAuthenticationInfo(String userId, String realmName) {
        return validTokenBySimpleAuthenticationInfo(userId, realmName, DEFAULT_EXPIRATION_TIME);
    }

}
