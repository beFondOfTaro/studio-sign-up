package com.iotstudio.studiosignup.shiro.token;

import com.iotstudio.studiosignup.shiro.StatelessAuthenticationToken;

public interface TokenUtil {

    String createDigest(StatelessAuthenticationToken token);

    String createDigest(StatelessAuthenticationToken token,long expirationTime);

    boolean validToken(StatelessAuthenticationToken clientToken);

    boolean validToken(StatelessAuthenticationToken clientToken, long delayTime);

    boolean deleteToken(Integer userId);
}
