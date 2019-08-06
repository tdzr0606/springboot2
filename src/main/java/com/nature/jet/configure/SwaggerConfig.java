package com.nature.jet.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * springboot2
 * SwaggerConfig
 *
 * @Author: 竺志伟
 * @Date: 2019-08-06 10:28
 */
@Configuration
public class SwaggerConfig
{
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.nature.jet.controller.api")).build();
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().title("News").description("News")
                .contact(new Contact("竺志伟", "", "tdzr_0606@126.com")).version("1.0").build();
    }
}
