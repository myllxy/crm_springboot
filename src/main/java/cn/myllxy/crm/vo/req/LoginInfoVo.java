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
}
