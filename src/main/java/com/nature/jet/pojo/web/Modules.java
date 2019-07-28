package com.nature.jet.pojo.web;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * Modules
 * Author:竺志伟
 * Date:2019-07-28 13:40:31
 */ 

@Data 
public class Modules implements Serializable
{
    private String note;
    private Boolean isPublic;
    private Integer id;
    private String title;
    private String url;
    private Integer parentId;

}
