package com.cloudstudio.security.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 定义跳转页面等常亮
 */
@ConfigurationProperties(prefix = "authentication")
@Component
public class Authentication {

    private String loginPage;
    private String loginProcessingUrl;
    private String username;
    private String password;
    private String[] staticPaths ;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getStaticPaths() {
        return staticPaths;
    }

    public void setStaticPaths(String[] staticPaths) {
        this.staticPaths = staticPaths;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "loginPage='" + loginPage + '\'' +
                ", loginProcessingUrl='" + loginProcessingUrl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", staticPaths=" + Arrays.toString(staticPaths) +
                '}';
    }
}
