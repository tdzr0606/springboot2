package com.nature.jet.controller.web;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.controller.system.BaseController;
import com.nature.jet.pojo.web.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * springboot2
 * IndexController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 22:34
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    HttpServletRequest request;

    /**
     * To login model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-19 23:03:22
     */
    @RequestMapping(value = "/web/login")
    public ModelAndView toLogin()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("/web/login");
        return modelAndView;
    }

    /**
     * 登录
     * Login common result.
     *
     * @param loginName the login name
     * @param loginPass the login pass
     * @return the common result
     * @author:竺志伟
     * @date :2019-03-19 23:05:39
     */
    @RequestMapping(value = "/web/loginAction")
    @ResponseBody
    public CommonResult login(@RequestParam(value = "loginName", required = true, defaultValue = "") String loginName,
                              @RequestParam(value = "loginPass", required = true, defaultValue = "") String loginPass)
    {
        logger.info("登录名:{},密码:{}", loginName, loginPass);
        Admin admin = new Admin();
        admin.setId(0);
        admin.setLoginName("admin");
        admin.setLoginPass("123456");
        admin.setUserName("竺志伟");
        super.setLoginAdmin(admin, request);
        return resultSuccessWrapper("", null);
    }

    /**
     * Log out model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-21 11:04:26
     */
    @RequestMapping(value = "/web/logOut")
    public ModelAndView logOut()
    {
        modelAndView = new ModelAndView();
        super.clearLoginAdmin(request);
        modelAndView.setViewName("/web/login");
        return modelAndView;
    }

    /**
     * To index model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-19 22:35:48
     */
    @RequestMapping(value = "/web/")
    public ModelAndView toIndex()
    {
        logger.info("后台进入");
        modelAndView = new ModelAndView();
        modelAndView.setViewName("/web/index");
        return modelAndView;
    }
}
