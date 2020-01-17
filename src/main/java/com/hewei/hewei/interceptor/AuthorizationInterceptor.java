package com.hewei.hewei.interceptor;

import com.hewei.hewei.base.Constant;
import com.hewei.hewei.base.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，判断此次请求是否有权限
 */

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisService redisService;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // String requestURI = request.getRequestURI();
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        // 从header中得到token
        String token = request.getHeader(Constant.AUTHORIZATION);
        if (redisService.checkToken(token)) {
            // 如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(Constant.CURRENT_USER_ID, redisService.getUserId(token));
            return true;
        }
        // 如果验证token失败，返回401错误
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}
