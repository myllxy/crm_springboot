package cn.myllxy.crm.shiro;

import cn.myllxy.crm.constant.TokenConstant;
import cn.myllxy.crm.exception.BusinessException;
import cn.myllxy.crm.exception.code.BaseExceptionResponseEnum;
import cn.myllxy.crm.utils.ErrorResponse;
import cn.myllxy.crm.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {
    /**
     * 拦截option请求，让其不进行shiro鉴权，前后端分离才会出现
     * 在这个方法中，如果返回 true，则表示“通过”，走到下一个过滤器。
     * 如果没有下一个过滤器的话，表示具有了访问某个资源的权限。如果返回 false，
     * 则会调用 onAccessDenied 方法，
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS");
    }

    /**
     * 验证有没有携带token以及token是否过期
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            Subject subject = getSubject(servletRequest, servletResponse);
            String accessToken = request.getHeader(TokenConstant.ACCESSTOKEN);
            String refreshToken = request.getHeader(TokenConstant.REFRESHTOKEN);
            if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(refreshToken)) {
                throw new BusinessException(BaseExceptionResponseEnum.NOTOKEN);
            }
            if (JwtTokenUtil.isTokenExpired(accessToken)) {
                throw new BusinessException(BaseExceptionResponseEnum.REFRESHTOKEN);
            }
            EmployeePasswordToken employeePasswordToken = new EmployeePasswordToken(accessToken);
            //TODO 登录
            if (!subject.isAuthenticated()) {
                subject.login(employeePasswordToken);
            }
        } catch (BusinessException e) {
            ErrorResponse.errorResponse(servletResponse, e);
            return false;
        }
        return true;
    }
}
