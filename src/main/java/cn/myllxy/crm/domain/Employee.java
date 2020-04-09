package cn.myllxy.crm.domain;

import cn.myllxy.crm.base.domain.BaseDomain;

public class Employee extends BaseDomain {
    //用户名
    private String username;
    //真实名
    private String realName;
    //密码
    private String password;
    //电话
    private String tel;
    //邮箱
    private String email;
    //录入时间
    private String inputTime;
    //状态     0 正常 ，-1离职
    private Integer state = 0;
    //0  普通员工    1   租户管理员      2超级管理员
    private Integer identity;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", inputTime='" + inputTime + '\'' +
                ", state=" + state +
                ", identity=" + identity +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInputTime() {
        return inputTime;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
