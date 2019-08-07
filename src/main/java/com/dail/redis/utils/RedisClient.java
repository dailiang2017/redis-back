package com.dail.redis.utils;

import com.dail.redis.constants.RedisConstant;
import com.dail.redis.results.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Auther: dailiang
 * @Date: 2018/12/26 14:36
 * @Description:
 */
@Component
@Slf4j
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;


    /**
     * 获得缓存
     * @param key
     * @return
     */
    public <T> CacheResult<T> get(String key, Class<T> tClass) {
        CacheResult result = new CacheResult();
        result.setData(StringUtil.stringToBean(this.getBase(key), tClass));
        return result;
    }

    /**
     * 设置缓存，默认超时时间
     * @param key
     * @param value
     * @return
     */
    public <T> String set(String key, T value) {
        return this.setBase(key, StringUtil.beanToString(value), RedisConstant.redisToExpireDefault);
    }

    /**
     *
     * @param key
     * @param value
     * @param secondsToExpire 单位秒
     * @return
     */
    public <T> String set(String key, T value, int secondsToExpire) {
        return this.setBase(key, StringUtil.beanToString(value), secondsToExpire);
    }

    /**
     *
     * @param key
     * @param value
     * @param millisecondsToExpire 单位毫秒
     * @return
     */
    public String set(String key, Object value, long millisecondsToExpire) {
        return this.setBase(key, SerializeUtil.serialize(value), millisecondsToExpire);
    }

    /**
     * 设置超时时间
     * @param key
     * @param secondsToExpire
     * @return
     */
    public Long exipre(String key, int secondsToExpire) {
        return expireBase(key, secondsToExpire);
    }

    /**
     * 设置超时时间
     * @param key
     * @return
     */
    public Long exipre(String key) {
        return expireBase(key, RedisConstant.redisToExpireDefault);
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public String delete(String key) {
        Jedis jedis = null;
        String str = "";
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return str;
    }


    /**
     * 设置缓存
     * @param key
     * @param value
     * @param secondsToExpire 单位秒
     * @return
     */
    private String setBase(String key, String value, int secondsToExpire) {
        Jedis jedis = null;
        String str = "";
        try {
            jedis = jedisPool.getResource();
            // nx ： not exists, 只有key 不存在时才把key value set 到redis
            str = jedis.set(key, value, "nx", "ex", secondsToExpire);
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return str;
    }

    /**
     * 设置缓存
     * @param key
     * @param value
     * @param millisecondsToExpire 单位毫秒
     * @return
     */
    private String setBase(String key, String value, long millisecondsToExpire) {
        Jedis jedis = null;
        String str = "";
        try {
            jedis = jedisPool.getResource();
            // nx ： not exists, 只有key 不存在时才把key value set 到redis
            str = jedis.set(key, value, "nx", "px", millisecondsToExpire);
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return str;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    private String getBase(String key) {
        Jedis jedis = null;
        String str = "";
        try {
            jedis = jedisPool.getResource();
            str = jedis.get(key);
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return str;
    }

    /**
     * 设置超时时间
     * @param key
     * @param secondsToExpire 单位秒
     * @return
     */
    private Long expireBase(String key, int secondsToExpire) {
        Jedis jedis = null;
        Long result = 0L;
        try {
            jedis = jedisPool.getResource();
            result = jedis.expire(key, secondsToExpire);
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return result;
    }

}
