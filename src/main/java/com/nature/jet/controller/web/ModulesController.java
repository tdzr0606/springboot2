package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import com.nature.jet.service.business.ModuleBusinessService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.web.ModulesService;
import com.nature.jet.pojo.web.Modules;

import java.util.LinkedList;
import java.util.List;

/**
 * Modules控制
 * ModulesController
 * Author:竺志伟
 * Date:2019-07-28 13:40:31
 */
@Controller
public class ModulesController extends BaseController
{
    @Autowired
    ModulesService modulesService;
    @Autowired
    ModuleBusinessService moduleBusinessService;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/modules/toPage")
    @RequiresPermissions(value = "module:show")
    public ModelAndView toPage()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("web/modulesInfo");
        return modelAndView;
    }

    /**
     * 获取分页信息
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/web/modules/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Modules> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                  @RequestParam(value = "page", required = true, defaultValue = "1") Integer page)
    {

        Page<Modules> dataPage = new Page<>();
        List<Modules> plist = modulesService.listParent();
        List<Modules> slist = modulesService.listSub();
        LinkedList<Modules> dataList = new LinkedList<>();
        for(Modules p : plist)
        {
            dataList.add(p);
            for(Modules s : slist)
            {
                if(s.getParentId().intValue() == p.getId())
                {
                    dataList.add(s);
                }
            }
        }
        dataPage.setPage(1);
        dataPage.setData(dataList);
        dataPage.setCount(dataList.size());
        dataPage.setTotalPage(1);
        dataPage.setCode(0);
        dataPage.setLimit(dataList.size());
        return dataPage;
    }

    /**
     * 添加信息
     *
     * @param modules
     * @return
     */
    @RequestMapping(value = "/web/modules/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(Modules modules)
    {
        if(modules.getParentId().intValue() != 0 && !modulesService.checkEnTitle(modules.getEnTitle()))
        {
            return resultFailsWrapper("当前英文名称已经存在", null);
        }
        return resultBoolWrapper(moduleBusinessService.add(modules), "信息创建成功", "信息创建失败", null);
    }

    /**
     * 修改信息
     *
     * @param modules
     * @return
     */
    @RequestMapping(value = "/web/modules/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult modify(Modules modules)
    {
        return resultBoolWrapper(modulesService.modify(modules), "信息修改成功", "信息修改失败", null);
    }

    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/web/modules/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", required = true, defaultValue = "0") String ids)
    {
        modulesService.deleteByIds(ids.split(","));
        return resultBoolWrapper(true, "信息删除成功", "信息删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/modules/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getModulesById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", modulesService.findById(id));
    }


    /**
     * List public parent common result.
     *
     * @return the common result
     * @author:竺志伟
     * @date :2019-07-28 14:28:58
     */
    @RequestMapping(value = "/web/modules/listPublicParent", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult listPublicParent()
    {
        return resultSuccessWrapper("", modulesService.listPublicParent());
    }

    /**
     * 启用禁用
     * Use common result.
     *
     * @param id the id
     * @return the common result
     * @author:竺志伟
     * @date :2019-07-28 15:09:23
     */
    @RequestMapping(value = "/web/modules/set", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult use(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        Modules modules = modulesService.findById(id);
        if(modules.getParentId().intValue() == 0)
        {
            modulesService.useByParentId(id, (modules.getIsPublic()) ? 0 : 1);
        }
        return resultBoolWrapper(modulesService.useById(id), "信息启用/禁用成功", "信息启用/禁用失败", null);
    }
}
