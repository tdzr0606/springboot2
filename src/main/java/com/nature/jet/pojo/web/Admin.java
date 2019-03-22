package com.nature.jet.pojo.web;

import lombok.Data;

import java.io.Serializable;

/**
 * Admin
 * Author:竺志伟
 * Date:2019-03-22 09:12:53
 */

@Data
public class Admin implements Serializable
{
    private String note;
    private String loginName;
    private String loginPass;
    private Boolean isPublic;
    private Integer id;
    private String userName;

}
