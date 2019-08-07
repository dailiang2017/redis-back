package com.dail.redis.enums;

/**
 * @Auther: dailiang
 * @Date: 2019/1/14 18:49
 * @Description: 状态 0-未认证，1-正常状态，2-已删除
 */
public enum UserStatusEnum {

    UNCERTIFIED(0,"未认证"),
    NORMAL(1, "正常"),
    DELETED(2, "已删除"),
    ;

    private Integer code;
    private String remark;
    private UserStatusEnum(Integer code, String remark) {
        this.code = code;
        this.remark = remark;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
