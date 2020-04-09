package cn.myllxy.crm.shiro;

import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.mapper.EmployeeMapper;
import cn.myllxy.crm.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author myllxy
 * @create 2020-03-18 9:37
 */
@Slf4j
public class EmployeeRealm extends AuthorizingRealm {
//    @Autowired
//    private EmployeeMapper employeeMapper;

    /**
     * 身份认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        EmployeePasswordToken token = (EmployeePasswordToken) authenticationToken;
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
    }

    //    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        EmployeePasswordToken token = (EmployeePasswordToken) authenticationToken;
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getPrincipal(), token.getPrincipal(), getName());
//        return simpleAuthenticationInfo;
//    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}

