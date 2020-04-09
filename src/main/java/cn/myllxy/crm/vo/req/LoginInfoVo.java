package cn.myllxy.crm.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author myllxy
 * @create 2020-03-17 16:02
 */
@Data
public class LoginInfoVo {
    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public LoginInfoVo() {
    }

    @Override
    public String toString() {
        return "LoginInfoVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
