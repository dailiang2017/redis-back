package com.dail.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: dailiang
 * @Date: 2018/12/26 15:24
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "redis", ignoreInvalidFields = false)
public class RedisProperties {

    private String host;
    private int port;
    private int timeout;
    private String password;
}
