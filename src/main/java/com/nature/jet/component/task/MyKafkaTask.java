package com.nature.jet.component.task;

import com.nature.jet.component.utils.MyKafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * kafka定时器
 * springboot2
 * TestTask
 *
 * @Author: 竺志伟
 * @Date: 2019-03-21 15:02
 */
@Component
@Slf4j
public class MyKafkaTask
{
    @Autowired
    MyKafkaSender kafkaSender;

    //    @Scheduled(cron = "0/3 * * * * ? ")
    public void sendTask()
    {
        log.info("定时发送消息开始");
        kafkaSender.sendMessage(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        log.info("定时发送消息结束");
    }
}
