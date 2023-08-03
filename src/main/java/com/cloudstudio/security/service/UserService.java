package com.cloudstudio.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 模拟查询用户信息
 */
@Component("userService")
public class UserService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * spring提供的加密算法，该算法只能验证不嫩解密
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("输入的账号: " + username);
        if(!"CloudStudio".equalsIgnoreCase(username)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        String password = passwordEncoder.encode("1234");
        //模拟返回权限
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
