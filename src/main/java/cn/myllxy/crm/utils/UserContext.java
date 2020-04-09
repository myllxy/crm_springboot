package cn.myllxy.crm.utils;

import cn.myllxy.crm.domain.Employee;
import org.apache.shiro.SecurityUtils;

import javax.servlet.http.HttpSession;

public class UserContext {
    public static final String USER_IN_SESSION = "user_in_session";


    /**
     * 存储到session中（web中的session）
     *
     * @param session
     */
    public static void setUser(HttpSession session) {
        session.setAttribute(USER_IN_SESSION, getEmployee());
    }

    /**
     * 在session中取值
     *
     * @return
     */
    public static Employee getEmployee() {
        return (Employee) SecurityUtils.getSubject().getPrincipal();
    }
}