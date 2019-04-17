package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import com.nature.jet.service.au.PhotosListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.au.PhotosService;
import com.nature.jet.pojo.au.Photos;

import java.sql.Timestamp;

/**
 * Photos控制
 * PhotosController
 * Author:竺志伟
 * Date:2019-04-15 20:55:30
 */
@Controller
public class PhotosController extends BaseController
{
    @Autowired
    PhotosService photosService;
    @Autowired
    PhotosListService photosListService;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/photos/toPage")
    public ModelAndView toPage()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("web/photosInfo");
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
    @RequestMapping(value = "/web/photos/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Photos> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                 @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                 @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return photosService.listPage(page, rows, key);
    }

    /**
     * 添加信息
     *
     * @param photos
     * @return
     */
    @RequestMapping(value = "/web/photos/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(Photos photos)
    {
        photos.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return resultBoolWrapper(photosService.add(photos), "信息创建成功", "信息创建失败", null);
    }

    /**
     * 修改信息
     *
     * @param photos
     * @return
     */
    @RequestMapping(value = "/web/photos/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult modify(Photos photos)
    {
        return resultBoolWrapper(photosService.modify(photos), "信息修改成功", "信息修改失败", null);
    }

    /**
     * 删除信息
     *
     * @param id the id
     * @return common result
     * @author:竺志伟
     * @date :2019-04-15 22:11:08
     */
    @RequestMapping(value = "/web/photos/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        if(photosListService.countByPhotosId(id) > 0)
        {
            return resultFailsWrapper("当前企业风采有相册信息,禁止删除", null);
        }
        photosService.deleteById(id);
        return resultBoolWrapper(true, "信息删除成功", "信息删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/photos/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getPhotosById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", photosService.findById(id));
    }
}
