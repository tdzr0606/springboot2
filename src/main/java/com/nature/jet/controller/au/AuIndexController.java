package com.nature.jet.controller.au;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import com.nature.jet.controller.system.BaseController;
import com.nature.jet.pojo.au.News;
import com.nature.jet.pojo.au.Photos;
import com.nature.jet.service.au.NewsService;
import com.nature.jet.service.au.PhotosListService;
import com.nature.jet.service.au.PhotosService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * springboot2
 * IndexController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-23 15:17
 */
@Controller
@Slf4j
public class AuIndexController extends BaseController
{
    @Autowired
    NewsService newsService;
    @Autowired
    PhotosService photosService;
    @Autowired
    PhotosListService photosListService;

    /**
     * 进入 新闻页面
     * To news model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-23 15:18:29
     */
    @RequestMapping(value = "/news")
    public ModelAndView toNews(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page)
    {
        Page<News> newsPage = newsService.listPublicPage(page, 16);
        modelAndView = new ModelAndView();
        modelAndView.addObject("count", newsPage.getCount());
        modelAndView.addObject("page", newsPage.getPage());
        modelAndView.addObject("newsList", newsPage.getData());
        modelAndView.addObject("nav", "news");
        modelAndView.setViewName("au/news");
        return modelAndView;
    }


    /**
     * To news detail model and view.
     *
     * @param id the id
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-23 15:33:18
     */
    @RequestMapping(value = "/news/{id}")
    public ModelAndView toNewsDetail(@PathVariable(value = "id", required = true) Long id)
    {
        News news = newsService.findById(id);
        modelAndView = new ModelAndView();
        modelAndView.addObject("nav", "news");
        modelAndView.addObject("news", news);
        modelAndView.setViewName("au/newsDetail");
        return modelAndView;
    }


    /**
     * To case model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-23 15:43:16
     */
    @RequestMapping(value = "/case")
    public ModelAndView toCase()
    {
        modelAndView = new ModelAndView();
        modelAndView.addObject("nav", "case");
        modelAndView.setViewName("au/case");
        return modelAndView;
    }


    /**
     * To about model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-03-23 15:44:18
     */
    @RequestMapping(value = "/about")
    public ModelAndView toAbout()
    {
        modelAndView = new ModelAndView();
        modelAndView.addObject("nav", "about");
        modelAndView.setViewName("au/about");
        return modelAndView;
    }


    /**
     * To photos model and view.
     *
     * @return the model and view
     * @author:竺志伟
     * @date :2019-04-15 22:21:50
     */
    @RequestMapping(value = "/photos")
    public ModelAndView toPhotos(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page)
    {
        Page<Photos> photosPage = photosService.listPublicPage(page, 16);
        modelAndView = new ModelAndView();
        modelAndView.addObject("nav", "photos");
        modelAndView.addObject("count", photosPage.getCount());
        modelAndView.addObject("page", photosPage.getPage());
        modelAndView.addObject("photosList", photosPage.getData());
        modelAndView.setViewName("au/photos");
        return modelAndView;
    }


    /**
     * Gets photos details.
     *
     * @param id the id
     * @return the photos details
     * @author:竺志伟
     * @date :2019-04-16 19:19:41
     */
    @RequestMapping(value = "/photos/details")
    @ResponseBody
    public CommonResult getPhotosDetails(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultSuccessWrapper("", photosListService.listByPhotosId(id));
    }
}
