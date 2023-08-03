package com.cloudstudio.security.authentication;

import com.cloudstudio.security.entity.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 认证成功后，响应JSON字符串
        Result result = Result.ok("认证成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(result.toJsonString());
    }
}
