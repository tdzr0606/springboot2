package com.nature.jet.controller.system;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.pojo.web.Admin;
import com.nature.jet.utils.Fields;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController
{
    @Autowired
    CommonResult commonResult;
    @Autowired
    protected HttpServletRequest request;
    protected ModelAndView modelAndView = null;


    /**
     * 方法描述：只带数据对象的CommonResult
     * 创建作者：李兴武
     * 创建日期：2017-06-23 15:19:22
     *
     * @param obj 数据对象
     * @return 只带数据对象的CommonResult
     */
    protected CommonResult resultDataWrapper(Object obj)
    {
        return resultSuccessWrapper("success", obj);
    }


    /**
     * 方法描述：自定义状态码的CommonResult
     * 创建作者：李兴武
     * 创建日期：2017-06-23 15:21:05
     *
     * @param code    自定义代码
     * @param message 提示消息
     * @param obj     数据对象
     * @return CommonResult
     */
    protected CommonResult resultWrapper(int code, String message, Object obj)
    {
        commonResult.setMsg(message);
        commonResult.setData(obj);
        commonResult.setCode(code);
        return commonResult;
    }

    /**
     * 方法描述：返回成功和失败的CommonResult
     * 创建作者：李兴武
     * 创建日期：2017-06-23 15:04:03
     *
     * @param bool    状态:true成功,false失败
     * @param success true成功 提示消息
     * @param fails   false失败 提示消息
     * @param obj     需要传递的数据对象
     * @return CommonResult对象
     */
    protected CommonResult resultBoolWrapper(boolean bool, String success, String fails, Object obj)
    {
        if(bool)
        {
            return resultSuccessWrapper(success, obj);
        }
        return resultFailsWrapper(fails, obj);
    }

    /**
     * 方法描述：返回成功的CommonResult
     * 创建作者：罗
     * 创建日期：2017-06-23 15:01:58
     *
     * @param message 成功提示的消息
     * @param obj     需要传递的数据对象
     * @return CommonResul对象
     */
    protected CommonResult resultSuccessWrapper(String message, Object obj)
    {
        return resultWrapper(CommonResult.SUCCESS, message, obj);
    }

    /**
     * 方法描述：返回失败的CommonResult
     * 创建作者：罗
     * 创建日期：2017-06-23 15:01:58
     *
     * @param message 失败提示的消息
     * @param obj     需要传递的数据对象
     * @return CommonResul对象
     */
    protected CommonResult resultFailsWrapper(String message, Object obj)
    {
        return resultWrapper(CommonResult.FAILS, message, obj);
    }


    /**
     * 方法描述: 获取容器根目录.
     * 创建日期: 2017-08-45 00:45:50
     * 创建作者: 李兴武
     *
     * @return java.lang.String
     */
    protected String getRootPath()
    {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
    }


    /**
     * 获取 访问ip地址
     * Gets request ip.
     *
     * @param request the request
     * @return the request ip
     * @author:竺志伟
     * @date :2018-09-13 19:31:32
     */
    protected String getRequestIp(HttpServletRequest request)
    {
        String ip = null;
        if(request.getHeader("x-forwarded-for") == null)
        {
            ip = request.getRemoteAddr();
        }
        else
        {
            ip = request.getHeader("x-forwarded-for");
        }
        return ip;
    }


    /**
     * Gets login admin.
     *
     * @return the login admin
     * @author:竺志伟
     * @date :2019-03-19 23:28:54
     */
    public Admin getLoginAdmin()
    {
        return (Admin) SecurityUtils.getSubject().getPrincipal();
    }

}
