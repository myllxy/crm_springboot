package cn.myllxy.crm.utils;

import cn.myllxy.crm.exception.BusinessException;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author myllxy
 * @create 2020-04-06 13:34
 */
public class ErrorResponse {
    public static void errorResponse(ServletResponse servletResponse, BusinessException e) throws IOException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        res.setHeader("Access-Control-Allow-Headers", "accessToken");
        res.setHeader("Access-Control-Allow-Headers", "refreshToken");
        res.setHeader("Content-Type", "application/json;charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setMsg(e.getMsg());
        ajaxResult.setCode(e.getCode());
        writer.write(JSON.toJSONString(ajaxResult));
        writer.close();
    }
}
