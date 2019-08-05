package com.nature.jet.configure;

import com.nature.jet.filter.XssCodeFilter;
import net.sf.ehcache.CacheManager;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.servlet.Filter;

/**
 * 自定义 Bean配置
 * The type Nature bean configure.
 *
 * @author:竺志伟
 * @date :2019-03-19 22:55:08
 */
@Configuration
public class MyBeanConfigure
{

    @Value("${sigarLibPath}")
    private String sigarLibPath;

    /**
     * 系統環境
     * Create sigar sigar.
     *
     * @return the sigar
     * @author:竺志伟
     * @date :2019-08-05 13:29:38
     */
    @Bean
    public Sigar createSigar()
    {
        String path = System.getProperty("java.library.path");
        //为防止java.library.path重复加，此处判断了一下
        if(!path.contains(sigarLibPath))
        {
            if(System.getProperty("os.name").toLowerCase().indexOf("win") > -1)
            {
                path += ";" + sigarLibPath;
            }
            else
            {
                path += ":" + sigarLibPath;
            }
            System.setProperty("java.library.path", path);
        }
        return new Sigar();
    }


    /**
     * 緩存管理器
     * Eh cache manager factory bean cache manager.
     *
     * @return the cache manager
     * @author:竺志伟
     * @date :2019-08-05 13:29:49
     */
    @Bean(name = "ehcacheManager")
    public CacheManager ehCacheManagerFactoryBean()
    {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try
        {
            bean.setConfigLocation(resolver.getResource("classpath:ehcache.xml"));
            bean.setShared(true);
            bean.isSingleton();
            return bean.getObject();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 過濾器配置
     * Filter registration bean filter registration bean.
     *
     * @return the filter registration bean
     * @author:竺志伟
     * @date :2019-08-05 13:30:04
     */
    @Bean
    public FilterRegistrationBean xssCodeFilterConf()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(xssCodeFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("xssCodeFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    /**
     * xsscode 過濾器
     * Xss code filter filter.
     *
     * @return the filter
     * @author:竺志伟
     * @date :2019-08-05 13:30:15
     */
    @Bean
    public Filter xssCodeFilter()
    {
        return new XssCodeFilter();
    }
}
