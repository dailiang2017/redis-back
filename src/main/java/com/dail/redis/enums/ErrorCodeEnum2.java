package com.dail.redis.enums;

/**
 *
 *  错误码五位，1位字母+4位数字
 *  以  E 开头的错误信息(服务接口出错、数据库不能连接、网络不能连接等)
 *  以  W 开头的警告信息(提交非法数据、)
 *  以  I 开头的提示信息(字段验证等)
 * @Auther: dailiang
 * @Date: 2019/1/14 18:19
 * @Description: 认证字段枚举
 */
public enum ErrorCodeEnum2 {

    SUCCESS("I0000","成功"),
    UNKNOW_EXCEPTION("E0001","未知错误"),
    VALIDATION_ERROR("E0002","数据验证错误"),
    APP_VERSION_ERROR("E0002","当前版本过低,请卸载后,重新下载安装."),
    NAME_OR_PASSWORD_FAILURE("E0003","用户名或密码错误!"),
    HYSTRIX_CALL_ERROR("E0004","服务调用异常"),
    FEIGN_EXCEPTION("E0005","服务调用异常"),
    NETFLIX_CLIENT_EXCEPTION("E0006","服务不可用"),
    PROGRAM_VERSION_ERROR("E0007","软件版本不一致"),
    TOKEN_EXPIRE("W0001","认证过期"),
    REQUEST_EXPIRE("W0002","请求超时"),
    SIGNATURE_INVALID("W0003","签名错误"),
    PARM_ERROR("W0004","提交数据错误"),	//提交的数据参数不对或不完整
    LOGIN_EXPIRE("W0005","登录过期，请重新登录"),	//登录过期
    PARA_ILLEGAL("W0006","不合法的参数，与认证不一致"),	//查询或编辑了非自己的数据
    REPEAT_LOGIN("W0007","该账户已在其它设备登录，请先退出。如果此登录非您本人操作，请联系供链通后台申诉。"),
    REPEAT_SUBMIT("W0008","请不要频繁操作"),
    NO_AUTH("W0009","该用户没有该权限"),
    LESS_AUTH("W0010","该用户该权限不够"),
    LESS_AUTH_AUTHORIZER("W0011","授权人权限不够高,授权失败!"),
    NULLPOINTEREXCEPTION("E0008","业务空异常"),
    DATABADE_EXCEPTION("E0009","数据库执行SQL异常"),
    BUSSINESS_EXCEPTION("E0010","业务异常"),
    Multipart_EXCEPTION("E0011","文件上传失败"),
    ;

    private String code;
    private String remark;
    private ErrorCodeEnum2(String code, String remark) {
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
