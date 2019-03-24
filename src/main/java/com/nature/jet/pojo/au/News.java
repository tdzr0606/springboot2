package com.nature.jet.pojo.au;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * News
 * Author:竺志伟
 * Date:2019-03-22 15:36:26
 */

@Data
public class News implements Serializable
{
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")  // json转换 format
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 数据提交  format
    private Timestamp createTime;
    private String author;
    private Boolean isPublic;
    private String fileurl;
    private Integer id;
    private String title;
    private String synopsis;
}
