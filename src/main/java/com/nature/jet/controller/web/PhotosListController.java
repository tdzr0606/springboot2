package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.au.PhotosListService;
import com.nature.jet.pojo.au.PhotosList;

/**
 * PhotosList控制
 * PhotosListController
 * Author:竺志伟
 * Date:2019-04-15 20:55:54
 */
@Controller
public class PhotosListController extends BaseController
{
    @Autowired
    PhotosListService photosListService;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/photosList/toPage")
    public ModelAndView toPage(@RequestParam(value = "photosId", required = true, defaultValue = "0") Integer photosId)
    {
        modelAndView = new ModelAndView();
        modelAndView.addObject("photosId", photosId);
        modelAndView.setViewName("web/photosListInfo");
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
    @RequestMapping(value = "/web/photosList/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<PhotosList> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                     @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                     @RequestParam(value = "key", required = false, defaultValue = "") String key,
                                     @RequestParam(value = "photosId", required = true, defaultValue = "0") Integer photosId)
    {
        return photosListService.listPage(page, rows, key,photosId);
    }

    /**
     * 添加信息
     *
     * @param photosList
     * @return
     */
    @RequestMapping(value = "/web/photosList/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(PhotosList photosList)
    {
        return resultBoolWrapper(photosListService.add(photosList), "信息创建成功", "信息创建失败", null);
    }

    /**
     * 修改信息
     *
     * @param photosList
     * @return
     */
    @RequestMapping(value = "/web/photosList/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult modify(PhotosList photosList)
    {
        return resultBoolWrapper(photosListService.modify(photosList), "信息修改成功", "信息修改失败", null);
    }

    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/web/photosList/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", required = true, defaultValue = "0") String ids)
    {
        photosListService.deleteByIds(ids.split(","));
        return resultBoolWrapper(true, "信息删除成功", "信息删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/photosList/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getPhotosListById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", photosListService.findById(id));
    }
}
