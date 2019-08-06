package com.nature.jet.controller.api;

import com.nature.jet.component.aspect.annotation.RateLimit;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.controller.system.BaseController;
import com.nature.jet.service.au.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * springboot2
 * ApiController
 *
 * @Author: 竺志伟
 * @Date: 2019-08-06 10:24
 */
@Controller
public class ApiController extends BaseController
{
    @Autowired
    NewsService newsService;

    /**
     * 展示新聞 前10條數據
     * News list common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2019-08-06 10:26:32
     */
    @RequestMapping(value = "/api/news/top10", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "新闻头10条",tags = {"新闻头10条"})
    @RateLimit(limitNum = 10)
    public CommonResult newsList()
    {
        return resultSuccessWrapper("", newsService.listPublicPage(1, 10));
    }
}
