package com.nature.jet.component.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.nature.jet.component.aspect.annotation.RateLimit;
import com.nature.jet.component.system.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * springboot2
 * RateLimitAspect
 *
 * @Author: 竺志伟
 * @Date: 2019-08-06 13:33
 */
@Component
@Aspect
@Scope
@Slf4j
public class RateLimitAspect
{
    //用来存放不同接口的RateLimiter(key为接口名称，value为RateLimiter)
    private ConcurrentHashMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();
    private RateLimiter rateLimiter;


    /**
     * 限流 切点
     * Rate limt.
     *
     * @author:竺志伟
     * @date :2019-08-06 14:31:47
     */
    @Pointcut("@annotation(com.nature.jet.component.aspect.annotation.RateLimit)")
    public void rateLimt()
    {

    }


    /**
     * 切点 只切controller 中的限流
     * Rate limt.
     *
     * @author:竺志伟
     * @date :2019-08-06 14:09:51
     */
    @Pointcut("execution(* com.nature.jet.controller..*(..)) && rateLimt()")
    public void rateLimtController()
    {

    }

    /**
     * 处理 controller 切点
     * Around object.
     *
     * @param joinPoint the join point
     * @return the object
     * @throws NoSuchMethodException the no such method exception
     * @author:竺志伟
     * @date :2019-08-06 14:09:59
     */
    @Around("rateLimtController()")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws NoSuchMethodException
    {
        Object obj = null;
        //获取拦截的方法名
        Signature sig = joinPoint.getSignature();
        //获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        //返回被织入增加处理目标对象
        Object target = joinPoint.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //获取注解信息
        RateLimit annotation = currentMethod.getAnnotation(RateLimit.class);
        double limitNum = annotation.limitNum(); //获取注解每秒加入桶中的token
        String functionName = msig.getName(); // 注解所在方法名区分不同的限流策略

        //获取rateLimiter
        if(rateLimiterMap.containsKey(functionName))
        {
            rateLimiter = rateLimiterMap.get(functionName);
        }
        else
        {
            rateLimiterMap.put(functionName, RateLimiter.create(limitNum));
            rateLimiter = rateLimiterMap.get(functionName);
        }

        try
        {
            if(rateLimiter.tryAcquire())
            {
                //执行方法
                obj = joinPoint.proceed();
            }
            else
            {
                //拒绝了请求（服务降级）
                CommonResult result = new CommonResult();
                result.setCode(0);
                result.setMsg("系统繁忙,请稍后再试");
                result.setData(null);
                obj = result;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return obj;
        }
    }
}
