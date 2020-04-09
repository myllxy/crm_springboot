package cn.myllxy.crm.service.impl;

import cn.myllxy.crm.constant.TokenConstant;
import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.exception.BusinessException;
import cn.myllxy.crm.exception.code.BaseExceptionResponseEnum;
import cn.myllxy.crm.service.IEmployeeService;
import cn.myllxy.crm.service.ITokenService;
import cn.myllxy.crm.utils.AjaxResult;
import cn.myllxy.crm.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.HashMap;

/**
 * @author myllxy
 * @create 2020-03-25 16:22
 */
@Service
@Slf4j
public class TokenServiceImpl implements ITokenService {
    @Autowired
    ITokenService tokenService;
    @Autowired
    IEmployeeService employeeService;

    /**
     * accessToken过期时触发，重新创建一个accessToken返回给前端
     *
     * @param request
     * @return
     */
    @Override
    public AjaxResult refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(TokenConstant.REFRESHTOKEN);
        String newToken = null;
        Employee e = null;
        if (JwtTokenUtil.validateToken(refreshToken)) {
            //获取到用户名之后重新去数据库查，因为用户新课那可能会发生变化
            String username = JwtTokenUtil.getUserName(refreshToken);
            e = employeeService.getEmployeeByName(username);
            if (null == e) {
                throw new BusinessException(BaseExceptionResponseEnum.NOUSER);
            }
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            newToken = JwtTokenUtil.getAccessToken(e.getId(), claims);
        }
        AjaxResult ajaxResult = new AjaxResult();
        //705表示token刷新成功
        ajaxResult.setCode(BaseExceptionResponseEnum.REFRESHTOKENSUCCESS.getCode());
        ajaxResult.setMsg(BaseExceptionResponseEnum.REFRESHTOKENSUCCESS.getMsg());
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", e);
        map.put("accessToken", newToken);
        map.put("refreshToken", refreshToken);
        ajaxResult.setObject(map);
        return ajaxResult;
    }
}
