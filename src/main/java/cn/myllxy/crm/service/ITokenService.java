package cn.myllxy.crm.service;

import cn.myllxy.crm.utils.AjaxResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author myllxy
 * @create 2020-03-25 16:05
 */
public interface ITokenService {
    AjaxResult refreshToken(HttpServletRequest request);
}
