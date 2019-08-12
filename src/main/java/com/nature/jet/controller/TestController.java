package com.nature.jet.controller;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.utils.MyKafkaSender;
import com.nature.jet.controller.system.BaseController;
import com.nature.jet.utils.JwtUtils;
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

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Test common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2019-08-12 13:10:02
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public CommonResult test()
    {
        sender.sendMessage("aaaa");
        return resultSuccessWrapper("a", null);
    }


    @RequestMapping(value = "/test2")
    @ResponseBody
    public CommonResult test2()
    {
        return resultSuccessWrapper("", jwtUtils.createToken("/api/news/top10", "获取前10条新闻信息", 10));
    }
}
