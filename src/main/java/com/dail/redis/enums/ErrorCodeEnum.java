package com.dail.redis.enums;

/**
 *
 *  错误码五位，1位字母+4位数字
 *  以  1-5 开头的错误信息与HttpStatus一致
 *  以  6 开头的警告信息(提交非法数据、)
 *  以  7 开头的提示信息(业务异常等)
 * @Auther: dailiang
 * @Date: 2019/1/14 18:19
 * @Description: 认证字段枚举
 */
public enum ErrorCodeEnum {

    SUCCESS(200,"成功"),
    UNAUTHORIZED(600,"没有访问权限"),
    REPEAT_LOGIN(601,"该账户已在其它设备登录，请先退出。如果此登录非您本人操作，请联系客服申诉。"),
    LOGIN_EXPIRE(602,"登录过期，请重新登录"),
    REQUEST_FREQUENT(700,"访问过于频繁"),
    BUSSINESS_EXCEPTION(701,"业务异常"),
    ;

    private int code;
    private String remark;
    private ErrorCodeEnum(int code, String remark) {
        this.code = code;
        this.remark = remark;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
