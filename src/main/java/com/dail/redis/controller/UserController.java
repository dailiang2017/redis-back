package com.dail.redis.controller;

import com.dail.redis.dto.UserDTO;
import com.dail.redis.service.UserService;
import com.dail.redis.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: dail
 * @CreateDate: 2019/8/7 17:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @PostMapping("/addUser")
    public Integer addUser(@RequestBody UserDTO userDTO) {
        Long userId = ThreadLocalUtil.getTokenInfo().getId();
        userDTO.setCreator(userId);
        userDTO.setModifier(userId);
        return userService.insert(userDTO);
    }
}
