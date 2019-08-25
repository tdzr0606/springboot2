package com.nature.jet.component.utils.kafka;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MyKafkaSender
{
    @Autowired
    KafkaTemplate template;

    /**
     * 发送消息
     * Send message.
     *
     * @param topic   the topic  话题
     * @param message the message  内容
     * @author:竺志伟
     * @date :2019-08-25 14:54:20
     */
    public void sendMessage(String topic, String message)
    {
        template.send(topic, message);
    }

}
