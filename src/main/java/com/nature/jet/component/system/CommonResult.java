package com.nature.jet.component.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Component
public class CommonResult implements Serializable
{
    @Autowired
    HttpServletRequest request;
    public static final int SUCCESS = 1;

    public static final int FAILS = 0;

    private int code;

    private String msg;

    private Object data;

    public CommonResult()
    {
        this.code = CommonResult.SUCCESS;
        this.msg = "";
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public boolean isSuccess()
    {
        return this.code == CommonResult.SUCCESS;
    }
}
