package com.nature.jet.configure;

import com.nature.jet.interceptor.MyInterceptor;
import com.nature.jet.interceptor.TimesInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


/**
 * 自定义 web 配置
 * The type Nature web configuer.
 *
 * @author:竺志伟
 * @date :2019-03-19 22:54:38
 */
@Configuration
public class MyWebConfigure implements WebMvcConfigurer
{
    @Value("${web.upload-path}")
    String uploadFile;
    @Autowired
    MyInterceptor myInterceptor;
    @Autowired
    TimesInterceptor timesInterceptor;

    /**
     * 设置 强制访问路径
     * Configure path match.
     *
     * @param configurer the configurer
     * @author:竺志伟
     * @date :2019-03-19 13:54:07
     */
    public void configurePathMatch(PathMatchConfigurer configurer)
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
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
        registry.addResourceHandler("/files/**").addResourceLocations("file:" + uploadFile);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        // swagger 显示配置
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * 添加拦截设定
     * Add interceptors.
     *
     * @param registry the registry
     * @author:竺志伟
     * @date :2019-03-19 22:49:15
     */
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(timesInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/error", "/static/**", "/files/**");
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/", "/error", "/static/**", "/files/**", "/web/login", "/web/loginAction",
                        "/web/logOut");
    }
}
