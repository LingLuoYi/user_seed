package com.linglouyi.user.utils;

import com.linglouyi.user.UserApplication;
import com.linglouyi.user.config.InitConfig;
import com.linglouyi.user.entity.User;
import com.linglouyi.user.model.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author linglouyi
 */
@Slf4j
public class LoginUtil {

    private static String tokenName = UserApplication.ac.getBean(InitConfig.class).tokenName();

    private static RedisTemplate<String, Token<User>> redisTemplate = UserApplication.ac.getBean(InitConfig.class).getRedisTemplate();

    public static Optional<User> getLoginUser() {
        Object object = RequestContextHolder.getRequestAttributes();
        if (object == null) {
            return Optional.empty();
        }
        HttpServletRequest request = ((ServletRequestAttributes) object).getRequest();
        //先取请求头中的token
        String token = request.getHeader(tokenName);
        if (token == null) {
            //头中没有则去session中取
            token = String.valueOf(request.getSession().getAttribute(tokenName));
            if (token == null) {
                return Optional.empty();
            }
        }
        token = tokenName +":"+ token;
        if (!redisTemplate.hasKey(token)) {
            return Optional.empty();
        }
        Token<User> userToken = redisTemplate.opsForValue().get(token);
        if (userToken != null) {
            return Optional.ofNullable(userToken.getUser());
        }
        return Optional.empty();
    }

    public static String setLoginUser(User member) throws UnknownHostException {
        Object object = RequestContextHolder.getRequestAttributes();
        if (object == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) object).getRequest();
        Token<User> tokenModel = new Token<>();
        tokenModel.setId(DigestUtils.md5DigestAsHex((member.getUsername()+System.currentTimeMillis()).getBytes()));
        tokenModel.setUserName(member.getUsername());
        tokenModel.setIss(InetAddress.getLocalHost().getHostName());
        tokenModel.setTime(new Date());
        tokenModel.setLoginIp(IPUtil.getRealIp(request));
        tokenModel.setExp(30 * 24 * 60 * 60 * 1000L);
        tokenModel.setLastTime(new Date());
        tokenModel.setUser(member);
        seanToken(tokenModel);
        request.getSession().setAttribute(tokenName,tokenModel.getId());
        return tokenModel.getId();
    }

    public static Optional<Token<User>> getToken(String id){
        return Optional.ofNullable(redisTemplate.opsForValue().get(tokenName+":"+id));
    }

    public static void removeToken(String id){
        redisTemplate.delete(tokenName+":"+id);
    }

    public static void seanToken(Token<User> tokenModel){
        redisTemplate.opsForValue().set(tokenName+":"+tokenModel.getId(),tokenModel,30, TimeUnit.DAYS);
    }
}
