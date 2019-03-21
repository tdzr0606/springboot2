package com.nature.jet.pojo.web;

import lombok.Data;

import java.io.Serializable;

/**
 * springboot2
 * Admin
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 23:26
 */
@Data
public class Admin implements Serializable
{
    private Integer id;
    private String loginName;
    private String loginPass;
    private String userName;
    private Boolean isPublic;
}
