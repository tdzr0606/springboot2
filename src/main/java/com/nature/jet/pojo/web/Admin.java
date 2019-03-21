package com.nature.jet.pojo.web;

import java.io.Serializable;

/**
 * springboot2
 * Admin
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 23:26
 */
public class Admin implements Serializable
{
    private Integer id;
    private String loginName;
    private String loginPass;
    private String userName;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getLoginPass()
    {
        return loginPass;
    }

    public void setLoginPass(String loginPass)
    {
        this.loginPass = loginPass;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
