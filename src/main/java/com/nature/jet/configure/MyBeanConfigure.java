package com.nature.jet.configure;

import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
