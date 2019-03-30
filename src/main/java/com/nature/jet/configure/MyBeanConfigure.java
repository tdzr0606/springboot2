package com.nature.jet.configure;

import net.sf.ehcache.CacheManager;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

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
}
