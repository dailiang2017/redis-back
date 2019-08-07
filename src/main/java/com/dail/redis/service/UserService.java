package com.dail.redis.service;

import com.dail.redis.dto.UserDTO;
import com.dail.redis.mapper.UserMapper;
import com.dail.redis.utils.ExceptionUtil;
import com.dail.redis.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: dail
 * @CreateDate: 2019/8/7 10:36
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserDTO findByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<UserDTO> findAll() {
        return userMapper.selectAll();
    }

    public UserDTO findByName(String username) {
        return userMapper.findByName(username);
    }

    public Integer update(UserDTO userDTO) {
        ExceptionUtil.isTrue(userDTO == null, "参数不能为空");
        ExceptionUtil.isTrue(userDTO.getId() == null, "用户id不能为空");
        return userMapper.updateByPrimaryKeySelective(userDTO);
    }

    public Integer insert(UserDTO userDTO) {
        ExceptionUtil.isTrue(userDTO == null, "参数不能为空");
        ExceptionUtil.isTrue(StringUtil.isBlankOrEmpty(userDTO.getUsername()), "用户名不能为空");
        ExceptionUtil.isTrue(StringUtil.isBlankOrEmpty(userDTO.getRealname()), "姓名不能为空");
        // 暂时默认
        userDTO.setPassword("123456");
        return userMapper.insertSelective(userDTO);
    }
}
