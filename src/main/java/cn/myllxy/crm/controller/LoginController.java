package cn.myllxy.crm.controller;

import cn.myllxy.crm.service.ILoginService;
import cn.myllxy.crm.utils.AjaxResult;
import cn.myllxy.crm.vo.req.LoginInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    /**
     * 登录核心代码
     *
     * @param loginInfoVo
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestBody @Valid LoginInfoVo loginInfoVo) {
        return loginService.login(loginInfoVo);
    }


    @PostMapping("/logout")
    @ResponseBody
    public AjaxResult logout() {
        return loginService.logout();
    }

    @PostMapping("/test")
    @ResponseBody
    public AjaxResult test() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setMsg("测试成功");
        return ajaxResult;
    }
}
