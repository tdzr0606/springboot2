package com.nature.jet.controller.web;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.controller.system.BaseController;
import com.nature.jet.pojo.web.Admin;
import com.nature.jet.service.web.AdminService;
import com.nature.jet.utils.SigarUtils;
import lombok.extern.slf4j.Slf4j;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/**
 * springboot2
 * IndexController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 22:34
 */
@Controller
@Slf4j
public class IndexController extends BaseController
{
    @Autowired
    HttpServletRequest request;
    @Autowired
    AdminService adminService;
    @Autowired
    Sigar sigar;

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
        log.info("登录名:{},密码:{}", loginName, loginPass);
        Admin admin = adminService.login(loginName, loginPass);
        if(null == admin)
        {
            return resultFailsWrapper("用户名密码错误", null);
        }
        super.setLoginAdmin(admin, request);
        return resultSuccessWrapper("登录成功", null);
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
        modelAndView.setView(new RedirectView("/web/login"));
        return modelAndView;
    }

    /**
     * Show basic info admin.
     *
     * @return the admin
     * @author:竺志伟
     * @date :2019-03-22 11:21:36
     */
    @RequestMapping(value = "/web/showBasicInfo")
    @ResponseBody
    public CommonResult showBasicInfo()
    {
        return resultSuccessWrapper("", super.getLoginAdmin(request));
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
        log.info("后台进入");
        modelAndView = new ModelAndView();
        modelAndView.addObject("mem", SigarUtils.getMem(sigar));
        modelAndView.addObject("cpu", SigarUtils.getCpu(sigar));
        modelAndView.setViewName("/web/index");
        return modelAndView;
    }

    /**
     * Refresh cpu common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2019-03-24 14:22:06
     */
    @RequestMapping(value = "/web/refreshCpu")
    @ResponseBody
    public CommonResult refreshCpu()
    {
        log.info("更新cpu使用信息");
        return resultSuccessWrapper("", SigarUtils.getCpu(sigar));
    }

    /**
     * Refresh mem common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2019-03-24 14:32:00
     */
    @RequestMapping(value = "/web/refreshMem")
    @ResponseBody
    public CommonResult refreshMem()
    {
        log.info("更新内存使用信息");
        return resultSuccessWrapper("",SigarUtils.getMem(sigar));
    }
}
