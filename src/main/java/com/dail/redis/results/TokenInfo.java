package com.dail.redis.results;

import lombok.Data;

/**
 * @Auther: dailiang
 * @Date: 2019/1/7 16:28
 * @Description:
 */
@Data
public class TokenInfo {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * token信息
     */
    private String token;
}
