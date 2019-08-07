package com.dail.redis.constants;

/**
 * @Auther: dailiang
 * @Date: 2018/12/28 14:01
 * @Description:
 */
public class RedisConstant {

    /**
     * 缓存 默认超时时间一天，单位秒
     */
    public static final int redisToExpireDefault = 24 * 60 * 60;

    /**
     * token|cookie缓存 默认超时时间一分钟，单位秒
     */
    public static final int tokenToExpireDefault = 600;

    /**
     * 限频时长 1秒
     */
    public static final int frequencyToExpireDefault = 1;
}
