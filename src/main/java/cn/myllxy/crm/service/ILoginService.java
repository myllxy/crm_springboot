package cn.myllxy.crm.service;


import cn.myllxy.crm.base.service.IBaseService;
import cn.myllxy.crm.domain.Employee;
import cn.myllxy.crm.utils.AjaxResult;
import cn.myllxy.crm.vo.req.LoginInfoVo;

import javax.servlet.http.HttpSession;

public interface ILoginService extends IBaseService<Employee, Long> {
    AjaxResult login(LoginInfoVo loginInfoVo);

    AjaxResult logout();
}
