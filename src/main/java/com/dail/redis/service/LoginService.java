package com.dail.redis.service;

import com.dail.redis.dto.LoginDTO;
import com.dail.redis.dto.UserDTO;
import com.dail.redis.utils.ExceptionUtil;
import com.dail.redis.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: dail
 * @CreateDate: 2019/8/7 10:32
 */
@Service("loginService")
public class LoginService {

    @Autowired
    private UserService userService;

    public String login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        ExceptionUtil.isTrue(StringUtil.isBlankOrEmpty(username) || StringUtil.isBlankOrEmpty(password)
                , "用户名或密码不能为空！");
        UserDTO user = userService.findByName(username);
        ExceptionUtil.isTrue(user == null, "用户名或密码错误！");
        ExceptionUtil.isTrue(!user.getPassword().equals(password), "用户名或密码错误！");
        return "";
    }

    public String loginOut() {
        return "";
    }
}
