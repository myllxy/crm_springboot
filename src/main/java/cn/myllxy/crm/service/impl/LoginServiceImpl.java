package cn.myllxy.crm.service.impl;


import cn.myllxy.crm.base.mapper.BaseMapper;
import cn.myllxy.crm.base.service.impl.BaseServiceImpl;
import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.exception.BusinessException;
import cn.myllxy.crm.exception.code.BaseExceptionResponseEnum;
import cn.myllxy.crm.mapper.EmployeeMapper;
import cn.myllxy.crm.service.ILoginService;
import cn.myllxy.crm.utils.*;
import cn.myllxy.crm.vo.req.LoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class LoginServiceImpl extends BaseServiceImpl<Employee, Long> implements ILoginService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public BaseMapper<Employee, Long> getBaseMapper() {
        return employeeMapper;
    }


    /**
     * 我并没有选择在这里进行登录，因为后面的doCredentialsMatch需要EmployeePasswordToken，
     * 如果在这里登录的话需要使用UsernamePasswordToken，后面不能强行转换了
     * 我现在这里假装登录，向前端返回登录成功后的数据，前端跳转到主页面，这个时候会进到
     * shiro的Filter------------CustomAccessControlFilter，在这里面会校验当前是否登录，
     * 于是在这里进行登录
     *
     * @param loginInfoVo
     * @return
     */
    @Override
    public AjaxResult login(LoginInfoVo loginInfoVo) {
        AjaxResult result = new AjaxResult();
        Employee e = employeeMapper.getEmployeeByName(loginInfoVo.getUsername());
        if (null == e) {
            throw new BusinessException(BaseExceptionResponseEnum.NOUSER);
        }
        //TODO 校验密码
        SimpleHash simpleHash = new SimpleHash(Md5Util.ALGORITHMNAME, loginInfoVo.getPassword(), Md5Util.SALT, Md5Util.HASHITERATIONS);
        if (!e.getPassword().equals(simpleHash.toString())) {
            throw new BusinessException(BaseExceptionResponseEnum.PASSWORDERROR);
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", e.getUsername());
        //填充信息以后再写
        //TODO claims信息
        //claims.put("roles", employeeMapper.getRoles());
        String accessToken = JwtTokenUtil.getAccessToken(e.getId(), claims);
        String refreshToken = JwtTokenUtil.getRefreshToken(e.getId(), claims);
        HashMap<String, Object> map = new HashMap<>();
        //返回token给前端
        map.put("user", e);
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);
        result.setCode(BaseExceptionResponseEnum.LOGOUTSUCCESS.getCode());
        result.setMsg(BaseExceptionResponseEnum.LOGOUTSUCCESS.getMsg());
        result.setObject(map);
        return result;
    }

    /**
     * 登出用户
     *
     * @return
     */
    @Override
    public AjaxResult logout() {
        AjaxResult result = new AjaxResult();
        Subject subject = SecurityUtils.getSubject();
        log.info("subject.getPrincipals()={}", subject.getPrincipals());
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        //TODO 登出信息校验
        result.setMsg(BaseExceptionResponseEnum.USERHASLOGOUT.getMsg());
        result.setCode(BaseExceptionResponseEnum.USERHASLOGOUT.getCode());
        return result;
    }
}
