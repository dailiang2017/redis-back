package com.dail.redis.exception;

/**
 * @Auther: dailiang
 * @Date: 2019/1/14 18:24
 * @Description: 基础异常类
 */
public class BaseException extends RuntimeException {

    /**
     * 编码
     */
    protected int code;
    /**
     * 错误信息
     */
    protected String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
