package com.dail.redis.utils;

import com.alibaba.fastjson.JSON;

/**
 * @Auther: dailiang
 * @Date: 2018/12/25 17:54
 * @Description: string类型工具类
 */
public class StringUtil {

    /**
     * 判断string类型非空
     * @param str
     * @return
     */
    public static boolean isBlankOrEmpty(String str) {
        return (str == null || str.isEmpty()) ? true : false;
    }

    /**
     * 判断string类型非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isBlankOrEmpty(str);
    }

    public static <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return ""+value;
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 设置模糊查询
     * @param name
     * @return
     */
    public static String setLikeSQL(String name) {
        return "%" + name;
    }
}
