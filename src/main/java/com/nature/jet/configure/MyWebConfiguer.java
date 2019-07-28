package com.nature.jet.configure;

import com.nature.jet.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 自定义 web 配置
 * The type Nature web configuer.
 *
 * @author:竺志伟
 * @date :2019-03-19 22:54:38
 */
@Configuration
public class MyWebConfiguer extends WebMvcConfigurationSupport
{
    @Value("${web.upload-path}")
    String uploadFile;

    /**
     * 设置 强制访问路径
     * Configure path match.
     *
     * @param configurer the configurer
     * @author:竺志伟
     * @date :2019-03-19 13:54:07
     */
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer)
    {
        configurer.setUseSuffixPatternMatch(false).setUseTrailingSlashMatch(true);
    }

    /**
     * 添加静态资源
     * Add resource handlers.
     *
     * @param registry the registry
     * @author:竺志伟
     * @date :2019-03-19 13:55:39
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
        registry.addResourceHandler("/files/**").addResourceLocations("file:" + uploadFile);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    /**
     * 添加拦截设定
     * Add interceptors.
     *
     * @param registry the registry
     * @author:竺志伟
     * @date :2019-03-19 22:49:15
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry)
    {
        //        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
        //                .excludePathPatterns("/", "/error", " /static/**", "/files/**", "/web/login",
        // "/web/loginAction",
        //                        "/web/logOut");
    }
}
