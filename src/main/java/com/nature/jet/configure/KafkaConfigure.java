package com.nature.jet.configure;

import com.nature.jet.component.kafka.NewsKafka;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springboot2
 * KafkaConfigure
 *
 * @Author: 竺志伟
 * @Date: 2020-03-19 10:22
 */
@Configuration
@ConditionalOnProperty(prefix = "kafka", name = "open", havingValue = "true")
public class KafkaConfigure
{

    /**
     * News kafka news kafka.
     *
     * @return the news kafka
     * @author:竺志伟
     * @email :tdzr_0606@126.com
     * @date :2020-03-19 10:27:25
     */
    @Bean(value = "newsKafka")
    public NewsKafka newsKafka()
    {
        return new NewsKafka();
    }
}
