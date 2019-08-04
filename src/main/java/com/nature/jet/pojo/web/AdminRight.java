package com.nature.jet.pojo.web;

import lombok.Data;
import java.io.Serializable;

/**
 * 
 * AdminRight
 * Author:竺志伟
 * Date:2019-08-04 16:14:43
 */ 

@Data 
public class AdminRight  implements Serializable 
{
    private String roleEnTitle;
    private String rightText;
    private String roleTitle;
    private Integer roleId;
    private Integer adminId;
    private Integer id;

}
