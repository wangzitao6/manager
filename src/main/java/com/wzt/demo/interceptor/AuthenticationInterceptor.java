package com.wzt.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {


        // 从 session中获取，自动登录的时候会重置session中的 token
        String token = (String) httpServletRequest.getSession().getAttribute("TOKEN");
        log.info("session token = {}", token);
        log.info("----登录校验sessionId-->  " + httpServletRequest.getSession().getId());
        // 如果http请求头没有token则尝试从session中获取
        if (StringUtils.isEmpty(token)) {
            token = httpServletRequest.getHeader("token");
            log.info("header token = {}", token);
        }

        return true;
    }

}
