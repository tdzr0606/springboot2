package com.nature.jet.controller;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.utils.MyKafkaSender;
import com.nature.jet.controller.system.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * springboot2
 * TestController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-21 14:50
 */
@Controller
public class TestController extends BaseController
{
    @Autowired
    MyKafkaSender sender;

    @RequestMapping(value = "/test")
    @ResponseBody
    public CommonResult test()
    {
        sender.sendMessage("aaaa");
        return resultSuccessWrapper("a", null);
    }
}
