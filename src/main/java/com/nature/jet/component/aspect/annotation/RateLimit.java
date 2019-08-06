package com.nature.jet.component.aspect.annotation;

import java.lang.annotation.*;

/**
 * 限流 注解
 * springboot2
 * Author: 竺志伟
 * Date:   2019-08-06 13:28
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit
{
    double limitNum() default 50;  //默认并发量
}
