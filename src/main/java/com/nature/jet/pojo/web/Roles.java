package com.nature.jet.pojo.web;

import lombok.Data;
import java.io.Serializable;

/**
 * 
 * Roles
 * Author:竺志伟
 * Date:2019-07-28 12:05:00
 */ 

@Data 
public class Roles  implements Serializable 
{
    private String note;
    private String cnName;
    private String enName;
    private Boolean isPublic;
    private Integer id;

}
