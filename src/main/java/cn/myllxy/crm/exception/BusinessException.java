package cn.myllxy.crm.exception;

import cn.myllxy.crm.exception.code.ExceptionResponse;

/**
 * @author myllxy
 * @create 2020-04-06 11:00
 */
public class BusinessException extends RuntimeException {
    private Integer code;
    private String msg;

    public BusinessException(ExceptionResponse response) {
        super(response.getMsg());
        this.msg = response.getMsg();
        this.code = response.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
