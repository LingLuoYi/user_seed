package com.linglouyi.user.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.linglouyi.user.entity.User;
import com.linglouyi.user.model.R;
import com.linglouyi.user.model.Token;
import com.linglouyi.user.utils.LoginUtil;
import com.linglouyi.user.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

/**
 * @author linglouyi
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Value("${system.token}")
    private String tokenName = "x-token";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(this.tokenName);
        if (token == null || "".equals(token)) {
            HttpSession session = request.getSession();
            token = String.valueOf(session.getAttribute(this.tokenName));
            if (token == null || "".equals(token)) {
                failure(request, response);
                return false;
            }
        }
        Optional<Token<User>> optionalTokenModel = LoginUtil.getToken(token);
        if (!optionalTokenModel.isPresent()) {
            failure(request, response);
            LoginUtil.removeToken(token);
            return false;
        }
        Token<User> tokenModel = optionalTokenModel.get();
        //验证过期
        if (!TimeUtils.timeOut(tokenModel.getLastTime(), tokenModel.getExp())) {
            failure(request, response);
            LoginUtil.removeToken(token);
            return false;
        }
        response.addHeader(this.tokenName, tokenModel.getId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //更新token
        Optional<Token<User>> optionalTokenModel = LoginUtil.getToken(tokenName);
        if (optionalTokenModel.isPresent()) {
            Token<User> tokenModel = optionalTokenModel.get();
            tokenModel.setLastTime(new Date());
            LoginUtil.seanToken(tokenModel);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    private void failure(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(401);
        response.getWriter().write(JSONObject.toJSONString(R.error(-1,"没有登录")));
    }

}
