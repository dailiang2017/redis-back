package com.dail.redis.utils;


import com.dail.redis.exception.BusinessException;

/**
 * @Auther: dailiang
 * @Date: 2019/1/14 18:44
 * @Description: 异常工具类
 */
public class ExceptionUtil {

    /**
     * true 则抛出业务异常
     * @param expression
     * @param error
     */
    public static void isTrue(boolean expression, String error) {
        if (expression) {
            throw new BusinessException(error);
        }
    }
}
