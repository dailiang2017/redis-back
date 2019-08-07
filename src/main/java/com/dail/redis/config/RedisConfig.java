package com.dail.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Auther: dailiang
 * @Date: 2018/12/26 14:15
 * @Description:
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * 日志管理
     */
    private Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Bean(name = "jedisPool")
    public JedisPool getJedisPool() {
        log.info("==>初始化jedis连接池");
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool pool = new JedisPool(config, redisProperties.getHost(), redisProperties.getPort(), redisProperties.getTimeout(), redisProperties.getPassword());
        return pool;
    }
}
