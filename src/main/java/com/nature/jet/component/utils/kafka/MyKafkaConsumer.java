package com.nature.jet.component.utils.kafka;

import lombok.extern.slf4j.Slf4j;
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

    /**
     * test 话题 获取内容
     * Consumer test.
     *
     * @param message the message
     * @author:竺志伟
     * @date :2019-08-25 14:54:06
     */
    @KafkaListener(topics = {"test"})
    public void consumerTest(String message)
    {
        log.info("test接收到信息:{}", message);
    }



}
