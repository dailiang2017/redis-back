package com.dail.redis.enums;

/**
 * @Auther: dailiang
 * @Date: 2019/1/16 14:06
 * @Description: N正常,Y删除
 */
public enum IsDeletedEnum {
    N("N","正常"),
    Y("Y", "删除")
    ;

    private String code;
    private String remark;
    private IsDeletedEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
