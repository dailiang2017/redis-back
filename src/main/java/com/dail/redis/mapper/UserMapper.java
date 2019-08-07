package com.dail.redis.mapper;

import com.dail.redis.dto.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserDAO继承基类
 */
public interface UserMapper extends MyBatisBaseMapper<UserDTO, Long> {

    @Select("select * from t_user")
    List<UserDTO> selectAll();

    @Select("select * from t_user where username = #{username}")
    UserDTO findByName(@Param(value = "username") String username);
}