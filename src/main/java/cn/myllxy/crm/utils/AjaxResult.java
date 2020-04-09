package cn.myllxy.crm.utils;

public class AjaxResult {
    /**
     * 默认是true
     */
    private Boolean success = true;
    /**
     * 返回给后台的信息
     */
    private String msg;
    /**
     * 返回给后台的校验码
     */
    private Integer code;

    /**
     * 返回给后台的对象
     */
    private Object object;

    public AjaxResult() {

    }

    public AjaxResult(Boolean success, String msg, Integer code, Object object) {
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.object = object;
    }

    public AjaxResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public AjaxResult(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


}
