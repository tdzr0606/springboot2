package com.nature.jet.component.aspect.annotation;

import java.lang.annotation.*;

/**
 * shiro 缓存清理 注解
 * springboot2
 * Author: 竺志伟
 * Date:   2019-09-16 10:37
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ShiroCacheClean
{
}
