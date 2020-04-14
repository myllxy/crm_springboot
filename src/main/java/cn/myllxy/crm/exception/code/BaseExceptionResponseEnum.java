package cn.myllxy.crm.exception.code;

/**
 * 自定义异常枚举，和前端约定好
 *
 * @author myllxy
 * @create 2020-04-06 11:12
 */
public enum BaseExceptionResponseEnum implements ExceptionResponse {
    NOTOKEN(702, "没有Token"),
    REFRESHTOKEN(703, "刷新Token"),
    USERHASLOGOUT(704, "用户已登出"),
    REFRESHTOKENSUCCESS(705, "刷新成功"),
    NOUSER(706, "没有该用户"),
    PASSWORDERROR(707, "密码错误"),
    LOGOUTSUCCESS(708, "登录成功"),
    SYSTEMBUSY(709, "系统繁忙"),
    NOKEYINREDIS(800, "Redis中没有该key"),
    REPEATTHEPRAISE(801, "用户不能重复点赞");

    private Integer code;
    private String msg;

    BaseExceptionResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
