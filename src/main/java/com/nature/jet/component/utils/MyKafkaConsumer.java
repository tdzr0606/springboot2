package com.nature.jet.component.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka消息接收
 * springboot2
 * KafkaConsumer
 *
 * @Author: 竺志伟
 * @Date: 2019-03-21 14:48
 */
@Component
@Slf4j
public class MyKafkaConsumer
{

    //    @KafkaListener(topics = {"test"})
    //    public void consumer(String message)
    //    {
    //        log.info("test接收到信息:{}", message);
    //    }
}
