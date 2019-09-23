package com.nature.jet.controller.web;

import com.nature.jet.component.utils.kafka.NewsKafka;
import com.nature.jet.utils.enu.NewsKafkaEnum;
import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import com.nature.jet.utils.NewsFileTools;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.au.NewsService;
import com.nature.jet.pojo.au.News;

import java.sql.Timestamp;

/**
 * News控制
 * NewsController
 * Author:竺志伟
 * Date:2019-03-22 15:36:26
 */
@Controller
public class NewsController extends BaseController
{
    @Autowired
    NewsService newsService;
    @Autowired
    NewsKafka newsKafka;
    @Value("${web.upload-path}")
    String rootPath;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/news/toPage")
    @RequiresPermissions(value = "news:show")
    public ModelAndView toPage()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("web/news");
        return modelAndView;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param rows
     * @param key
     * @return
     */
    @RequestMapping(value = "/web/news/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<News> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                               @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                               @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return newsService.listPage(page, rows, key);
    }

    /**
     * 添加信息
     *
     * @param news
     * @return
     */
    @RequestMapping(value = "/web/news/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(News news,
                            @RequestParam(value = "fileContent", required = true, defaultValue = "") String fileContent)
    {
        if(StringUtils.isNotBlank(fileContent))
        {
            news.setFileurl(NewsFileTools.saveToFile(rootPath, fileContent));
        }
        news.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return resultBoolWrapper(newsKafka.sendMessage(NewsKafkaEnum.NEW.getTopic(), news), "信息创建成功", "信息创建失败", null);
    }

    /**
     * 修改信息
     *
     * @param news
     * @return
     */
    @RequestMapping(value = "/web/news/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult modify(News news, @RequestParam(value = "fileContent", required = true, defaultValue = "")
            String fileContent)
    {
        if(StringUtils.isNotBlank(news.getFileurl()))
        {
            NewsFileTools.updateFile(rootPath + news.getFileurl(), fileContent);
        }
        else
        {
            news.setFileurl(NewsFileTools.saveToFile(rootPath, fileContent));
        }
        return resultBoolWrapper(newsKafka.sendMessage(NewsKafkaEnum.MODIFY.getTopic(), news), "信息修改成功", "信息修改失败",
                null);
    }

    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/web/news/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", required = true, defaultValue = "0") String ids)
    {
        return resultBoolWrapper(newsKafka.sendMessage(NewsKafkaEnum.DELETE.getTopic(), ids), "信息删除成功", "信息删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/news/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getNewsById(@RequestParam(value = "id", required = true, defaultValue = "0") String id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", newsService.findById(id));
    }
}
