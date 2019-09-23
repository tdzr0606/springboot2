package com.nature.jet.component.aspect;

import com.nature.jet.component.shiro.AdminRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * shiro缓存清理 切面处理
 * springboot2
 * ShiroCacheCleanAspect
 *
 * @Author: 竺志伟
 * @Date: 2019-09-16 10:38
 */
@Component
@Aspect
@Scope
@Slf4j
public class ShiroCacheCleanAspect
{
    @Autowired
    AdminRealm adminRealm;


    /**
     * 定义 注解 切点
     * Clean cache.
     *
     * @author:竺志伟
     * @date :2019-09-16 11:15:49
     */
    @Pointcut("@annotation(com.nature.jet.component.aspect.annotation.ShiroCacheClean)")
    public void cleanCache()
    {

    }

    /**
     * 定义 方法 切面
     * Clean cache service.
     *
     * @author:竺志伟
     * @date :2019-09-16 11:16:04
     */
    @Pointcut("execution(* com.nature.jet.service..*(..)) && cleanCache()")
    public void cleanCacheService()
    {

    }

    /**
     * 方法后切
     * After service.
     *
     * @param joinPoint the join point
     * @throws NoSuchMethodException the no such method exception
     * @author:竺志伟
     * @date :2019-09-16 11:16:15
     */
    @After("cleanCacheService()")
    public void afterService(JoinPoint joinPoint)
    {
        adminRealm.clearCached(SecurityUtils.getSubject().getPrincipals());
        log.info("shiro缓存清理");
    }


}
