package com.nature.jet.controller.web;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.controller.system.BaseController;
import com.nature.jet.pojo.web.Admin;
import com.nature.jet.service.web.AdminService;
import com.nature.jet.utils.Fields;
import com.nature.jet.utils.SigarUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.hyperic.sigar.Sigar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier(value = "adminSecruityManager")
    DefaultWebSecurityManager adminSecruityManager;


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
        modelAndView.setViewName("web/login");
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
        SecurityUtils.setSecurityManager(adminSecruityManager);
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPass);
        //获取当前的Subject
        Subject subject = SecurityUtils.getSubject();
        boolean pd = false;
        try
        {
            subject.login(token);
            pd = subject.isAuthenticated();
            if(pd)
            {
                request.getSession().setAttribute(Fields.SESSION_ADMIN, (Admin) subject.getPrincipal());
            }
            else
            {
                token.clear();
            }
        }
        catch(AuthenticationException e)
        {
            log.error("登录错误", e);
        }
        return resultBoolWrapper(pd, "登录成功", "用户名密码错误", null);
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
        SecurityUtils.getSubject().logout();
        request.getSession().removeAttribute(Fields.SESSION_ADMIN);
        modelAndView = new ModelAndView();
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
        return resultSuccessWrapper("", super.getLoginAdmin());
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
        modelAndView.setViewName("web/index");
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
        return resultSuccessWrapper("", SigarUtils.getMem(sigar));
    }
}
