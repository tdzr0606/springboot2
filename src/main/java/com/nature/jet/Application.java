package com.nature.jet;

import io.undertow.servlet.api.SecurityConstraint;
import io.undertow.servlet.api.SecurityInfo;
import io.undertow.servlet.api.TransportGuaranteeType;
import io.undertow.servlet.api.WebResourceCollection;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
public class Application
{
    @Value("${server.port}")
    Integer httpsPort;
    @Value("${server.http.port}")
    Integer httpPort;

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class);
    }


    @Bean
    public ServletWebServerFactory servletContainer()
    {
        UndertowServletWebServerFactory undertow = new UndertowServletWebServerFactory();
        //设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接, 默认设置每个CPU核心一个线程
        undertow.setIoThreads(4);
        //阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程,它的值设置取决于系统的负载
        undertow.setWorkerThreads(40);
        //每块buffer的空间大小,越小的空间被利用越充分
        undertow.setBufferSize(1024);
        //是否分配的直接内存
        undertow.setUseDirectBuffers(true);

        // 添加其他端口监听, 例如 8081 也可以访问,可以添加多个
        undertow.addBuilderCustomizers(builder ->
        {
            builder.addHttpListener(httpPort, "0.0.0.0");
        });

        // http 强制 转换 https  部分强制转换,例如 web 后台访问
        undertow.addDeploymentInfoCustomizers(deploymentInfo ->
        {
            deploymentInfo.addSecurityConstraint(new SecurityConstraint()
                    .addWebResourceCollection(new WebResourceCollection().addUrlPattern("/web/*"))
                    .setTransportGuaranteeType(TransportGuaranteeType.CONFIDENTIAL)
                    .setEmptyRoleSemantic(SecurityInfo.EmptyRoleSemantic.PERMIT))
                    .setConfidentialPortManager(exchange -> httpsPort);
        });

        return undertow;
    }

    //    @Bean
    //    public ServletWebServerFactory servletContainer()
    //    {
    //        JettyServletWebServerFactory jetty = new JettyServletWebServerFactory();
    //        jetty.addServerCustomizers(new JettyServerCustomizer()
    //        {
    //            @Override
    //            public void customize(Server server)
    //            {
    //                final QueuedThreadPool threadPool = server.getBean(QueuedThreadPool.class);
    //                threadPool.setMaxThreads(400);
    //                threadPool.setMinThreads(20);
    //            }
    //        });
    //        return jetty;
    //    }
}
