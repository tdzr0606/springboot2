package com.nature.jet.configure;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.nature.jet.component.shiro.AdminRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置项
 * springboot2
 * ShiroConfigure
 *
 * @Author: 竺志伟
 * @Date: 2019-07-21 15:56
 */
@Configuration
public class ShiroConfigure
{

    /**
     * shiro 校验过滤器
     * Shiro filter shiro filter factory bean.
     *
     * @param securityManager the security manager
     * @return the shiro filter factory bean
     * @author:竺志伟
     * @date :2019-07-27 16:20:02
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(
            @Qualifier(value = "adminSecruityManager") DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //默认跳转到登陆页面
        shiroFilterFactoryBean.setLoginUrl("/web/login");
        //登陆成功后的页面
        shiroFilterFactoryBean.setSuccessUrl("/web/");
        //无权限错误
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //权限控制map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //        filterChainDefinitionMap.put("/static/**", "anon");
        //        filterChainDefinitionMap.put("/files/**", "anon");
        //        filterChainDefinitionMap.put("/api/**","anon");
        //        filterChainDefinitionMap.put("/", "anon");
        //        filterChainDefinitionMap.put("/error", "anon");
        //        filterChainDefinitionMap.put("/common/error", "anon");
        //        filterChainDefinitionMap.put("/web/login", "anon");
        //        filterChainDefinitionMap.put("/web/loginAction", "anon");
        //        filterChainDefinitionMap.put("/web/logOut", "anon");
        filterChainDefinitionMap.put("/web/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * shiron 默认的 安全控制器
     * Security manager default web security manager.
     *
     * @param adminRealm the admin realm
     * @return the default web security manager
     * @author:竺志伟
     * @date :2019-07-27 16:20:22
     */
    @Bean(name = "adminSecruityManager")
    public DefaultWebSecurityManager securityManager(AdminRealm adminRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminRealm);
        return securityManager;
    }


    /**
     * shiro 生命周期 管理
     * Lifecycle bean post processor lifecycle bean post processor.
     *
     * @return the lifecycle bean post processor
     * @author:竺志伟
     * @date :2019-07-27 16:20:47
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 监听 通知器 为 注解做准备,必须在 bean生命周期管理之后启动
     * Advisor auto proxy creator default advisor auto proxy creator.
     *
     * @return the default advisor auto proxy creator
     * @author:竺志伟
     * @date :2019-07-27 16:28:02
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro 注解
     * Authorization attribute source advisor authorization attribute source advisor.
     *
     * @param securityManager the security manager
     * @return the authorization attribute source advisor
     * @author:竺志伟
     * @date :2019-07-27 16:29:15
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier(value = "adminSecruityManager") DefaultWebSecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * shiro thymeleaf 扩展标签控制
     * Shiro dialect shiro dialect.
     *
     * @return the shiro dialect
     * @author:竺志伟
     * @date :2019-07-27 16:46:40
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }
}
