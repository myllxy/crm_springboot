package cn.myllxy.crm.controller;

import cn.myllxy.crm.constant.TokenConstant;
import cn.myllxy.crm.service.ITokenService;
import cn.myllxy.crm.utils.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author myllxy
 * @create 2020-03-25 16:00
 */
@Controller
@CrossOrigin
@RequestMapping("/token")
public class TokenController {

    @Autowired
    ITokenService tokenService;

    @PostMapping("/refreshtoken")
    @ResponseBody
    public AjaxResult refreshToken(HttpServletRequest request) {
        return tokenService.refreshToken(request);
    }
}
