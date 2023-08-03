package com.cloudstudio.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomLoginController {

    /**
     * 访问thymeleaf页面
     * @return
     */
    @RequestMapping("/login/page")
    public String toLogin() {
        return "login";
    }
}
