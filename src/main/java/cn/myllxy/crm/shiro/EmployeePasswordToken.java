package cn.myllxy.crm.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class EmployeePasswordToken extends UsernamePasswordToken {
    private String token;

    public EmployeePasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}