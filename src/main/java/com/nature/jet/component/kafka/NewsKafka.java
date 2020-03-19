package com.nature.jet.component.kafka;

import com.nature.jet.pojo.au.News;
import com.nature.jet.service.au.NewsService;
import com.nature.jet.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * 新闻消息 kafka处理
 * springboot2
 * NewsKafka
 *
 * @Author: 竺志伟
 * @Date: 2019-08-25 15:56
 */
@Slf4j
public class NewsKafka extends CommonKafka
{
    /******************************** 新闻消息 生产者******************************************/

    /**
     * 发送消息 对象
     * Send news.
     *
     * @param news the news
     * @author:竺志伟
     * @date :2019-08-25 14:57:46
     */
    public boolean sendMessage(String topic, News news)
    {
        return this.sendMessage(topic, JsonUtils.toJson(news, null));
    }


    /******************************** 新闻消息 消费者******************************************/

    @Autowired
    NewsService newsService;

    /**
     * 接收 消息 新建处理
     * Consumer news.
     *
     * @param news the news
     * @author:竺志伟
     * @date :2019-08-25 14:58:48
     */
    @KafkaListener(topics = {"com.nature.news.new"})
    public void consumerNews(String news)
    {
        News obj = JsonUtils.parseJson(news, News.class);
        newsService.add(obj);
        log.info("新闻信息新建完成");
    }


    /**
     * 接收 消息 修改处理
     * Consumer modify.
     *
     * @param news the news
     * @author:竺志伟
     * @date :2019-08-25 15:56:05
     */
    @KafkaListener(topics = {"com.nature.news.modify"})
    public void consumerModify(String news)
    {
        News obj = JsonUtils.parseJson(news, News.class);
        newsService.modify(obj);
        log.info("新闻信息修改完成");
    }


    /**
     * 接收 消息 删除处理
     * Consumer delete.
     *
     * @param deleteIds the delete ids
     * @author:竺志伟
     * @date :2019-08-25 15:56:06
     */
    @KafkaListener(topics = {"com.nature.news.delete"})
    public void consumerDelete(String deleteIds)
    {
        newsService.deleteByIds(deleteIds.split(","));
        log.info("新闻信息删除完成");
    }
}
