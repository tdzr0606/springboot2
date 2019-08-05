package com.nature.jet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * springboot2
 * Application
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 22:20
 */
@SpringBootApplication
@MapperScan(value = "com.nature.jet.mapper") // Mybatis 扫描
@EnableKafka  //打开kafka消息机制
@EnableScheduling //打开定时器
@ServletComponentScan  //打開servlet掃描
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class);
    }


    @Bean
    public ServletWebServerFactory servletContainer()
    {
        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
        jetty.addServerCustomizers(new JettyServerCustomizer()
        {
            @Override
            public void customize(Server server)
            {
                final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
                threadPool.setMaxThreads(400);
                threadPool.setMinThreads(20);
            }
        });
        return jetty;
    }
}
