package com.cloudstudio.security.config;

import com.cloudstudio.security.entity.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private Authentication authentication;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 加入验证用户的service
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 核心方法，配置什么方法可以不通过鉴权就能访问，并指定一系列的过滤器
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登录方式
        http.formLogin()
            .loginPage(authentication.getLoginPage())
            // 登录表单提交处理url, 默认是/login
            .loginProcessingUrl(authentication.getLoginProcessingUrl())
            //验证账号
            .usernameParameter(authentication.getUsername())
            //验证密码
            .passwordParameter(authentication.getPassword())
            //指定访问成功/失败的处理类
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
            .and()
            .authorizeRequests()
            ///login/page所有人都能访问，要不没法登录
            .antMatchers(authentication.getLoginPage()).permitAll()
            //其余接口必须验证用户才能访问
            .anyRequest().authenticated()
        ;
    }

    /**
     * 静态资源不需要鉴权
     * @param web
     */
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/dist/**", "/modules/**", "/plugins/**");
    }
}
