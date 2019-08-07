package com.dail.redis.controller;

import com.dail.redis.dto.LoginDTO;
import com.dail.redis.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 登录
 * @Author: dail
 * @CreateDate: 2019/8/7 10:27
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }

    @GetMapping("/loginOut")
    public String loginOut() {
        return loginService.loginOut();
    }
}
