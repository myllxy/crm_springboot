package cn.myllxy.crm.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@Configuration
public class ShiroConfig {
    @Bean
    public CustomHashedCredentialsMatcher getEmployeeHashedCredentialsMatcher() {
        return new CustomHashedCredentialsMatcher();
    }

    @Bean
    public EmployeeRealm employeeRealm() {
        EmployeeRealm employeeRealm = new EmployeeRealm();
        employeeRealm.setCredentialsMatcher(getEmployeeHashedCredentialsMatcher());
        return employeeRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(employeeRealm());
        return securityManager;
    }

    /**
     * 配置shiroFilter拦截器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //自定义拦截器
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //禁止Option请求
        filtersMap.put("token", new CustomAccessControlFilter());
        //用来校验token
        shiroFilterFactoryBean.setFilters(filtersMap);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        //无需认证
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/like/likepost", "anon");
        filterChainDefinitionMap.put("/like/unlikepost", "anon");
        filterChainDefinitionMap.put("/token/refreshtoken", "anon");
        filterChainDefinitionMap.put("/user/logout", "anon");
        //需要认证
        filterChainDefinitionMap.put("/**", "token");
        //配置项目启动时默认进入页面
        //前后端分离项目中，后端只返回数据，真正的页面跳转交给前端来实现
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

}
