package cn.myllxy.crm.shiro;

import cn.myllxy.crm.exception.BusinessException;
import cn.myllxy.crm.exception.code.BaseExceptionResponseEnum;
import cn.myllxy.crm.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author myllxy
 * @create 2020-03-21 20:02
 */
public class CustomHashedCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        EmployeePasswordToken employeePasswordToken = (EmployeePasswordToken) token;
        String accessToken = (String) employeePasswordToken.getPrincipal();
//        if (JwtTokenUtil.isTokenExpired(accessToken)) {
//            throw new BusinessException(BaseExceptionResponseEnum.REFRESHTOKEN);
//        }
        return true;
    }

}
