package com.nature.jet.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * springboot2
 * SystemController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 23:40
 */
@Controller
public class SystemController extends BaseController
{

    /**
     * To index model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-19 23:45:11
     */
    @RequestMapping(value = "/")
    public ModelAndView toIndex()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


    /**
     * 自定义错误处理
     * Error model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-19 23:42:20
     */
    @RequestMapping(value = "/common/error")
    public ModelAndView error()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/error500");
        return modelAndView;
    }
}
