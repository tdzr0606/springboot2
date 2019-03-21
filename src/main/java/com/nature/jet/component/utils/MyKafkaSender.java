package com.nature.jet.component.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka消息发送
 * springboot2
 * KafkaSender
 *
 * @Author: 竺志伟
 * @Date: 2019-03-21 14:47
 */
@Component
public class MyKafkaSender
{
    @Autowired
    KafkaTemplate template;

    public void sendMessage(String message)
    {
        template.send("test", message);
    }
}
