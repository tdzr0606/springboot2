package com.nature.jet.pojo.web;

import lombok.Data;
import java.io.Serializable;

/**
 * 
 * RolesRight
 * Author:竺志伟
 * Date:2019-08-04 11:53:36
 */ 

@Data 
public class RolesRight  implements Serializable 
{
    private String moduleRoleTitle;
    private String moduleEnTitle;
    private String moduleTitle;
    private String moduleRoleEnTitle;
    private Integer rolesId;
    private Integer moduleRoleId;
    private Integer id;
    private Integer moduleId;

}
