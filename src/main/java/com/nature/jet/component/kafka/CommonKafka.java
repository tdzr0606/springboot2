package com.nature.jet.component.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * springboot2
 * CommonKafka
 *
 * @Author: 竺志伟
 * @Date: 2019-08-25 16:09
 */
@Slf4j
public class CommonKafka
{
    @Autowired
    KafkaTemplate template;

    /**
     * 发送消息 字符串
     * Send message boolean.
     *
     * @param topic   the topic
     * @param message the message
     * @return the boolean
     * @author:竺志伟
     * @date :2019-08-25 16:01:31
     */
    public boolean sendMessage(String topic, String message)
    {
        template.send(topic, message).addCallback(new ListenableFutureCallback()
        {
            @Override
            public void onFailure(Throwable throwable)
            {
                log.error("消息发送错误", throwable);
            }

            @Override
            public void onSuccess(Object o)
            {
                log.info("消息发送成功");
                SendResult result = (SendResult) o;
                log.info("----------------------");
                log.info("topic:{}", result.getProducerRecord().topic());
                log.info("key:{}", result.getProducerRecord().key());
                log.info("value:{}", result.getProducerRecord().value().toString());
                log.info("----------------------");
            }
        });
        return true;
    }
}
