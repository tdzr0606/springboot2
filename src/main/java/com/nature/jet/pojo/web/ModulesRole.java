package com.nature.jet.pojo.web;

import lombok.Data;

import java.io.Serializable;

/**
 * ModulesRole
 * Author:竺志伟
 * Date:2019-08-03 14:42:17
 */

@Data
public class ModulesRole implements Serializable
{
    private String note;
    private String enTitle;
    private Boolean isPublic;
    private Integer id;
    private String title;
    private Integer moduleId;

}
