package com.nature.jet.utils.enu;

/**
 * 新闻 kafka topic 配置
 * springboot2
 * Author: 竺志伟
 * Date:   2019-08-25 15:42
 */
public enum NewsKafkaEnum
{
    NEW("com.nature.news.new", "新建"),
    MODIFY("com.nature.news.modify", "修改"),
    DELETE("com.nature.news.delete", "删除");

    private String topic;
    private String title;

    private NewsKafkaEnum(String topic, String title)
    {
        this.topic = topic;
        this.title = title;
    }

    public String getTopic()
    {
        return topic;
    }
}
