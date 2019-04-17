package com.nature.jet.pojo.au;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

import lombok.Data;

import java.io.Serializable;

/**
 * Photos
 * Author:竺志伟
 * Date:2019-04-15 20:55:30
 */

@Data
public class Photos implements Serializable
{
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    private Integer id;
    private String title;
    private String note;
    private Boolean isPublic;
    private String imgUrl;
}
