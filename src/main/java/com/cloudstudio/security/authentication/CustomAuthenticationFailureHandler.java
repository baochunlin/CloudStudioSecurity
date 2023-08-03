package com.cloudstudio.security.authentication;

import com.cloudstudio.security.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证失败处理类
 */
@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 认证失败响应JSON字符串，
        Result result = Result.build(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        //此处指定返回对象为json，也可以改为返回值
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(result.toJsonString());
    }
}
