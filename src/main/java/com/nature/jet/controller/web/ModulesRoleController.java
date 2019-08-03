package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.web.ModulesRoleService;
import com.nature.jet.pojo.web.ModulesRole;

/**
 * ModulesRole控制
 * ModulesRoleController
 * Author:竺志伟
 * Date:2019-08-03 14:42:17
 */
@Controller
public class ModulesRoleController extends BaseController
{
    @Autowired
    ModulesRoleService modulesRoleService;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/modulesRole/toPage")
    public ModelAndView toPage(@RequestParam(value = "moduleId", required = true, defaultValue = "0") Integer moduleId)
    {
        modelAndView = new ModelAndView();
        modelAndView.addObject("moduleId", moduleId);
        modelAndView.setViewName("web/popular/modulesRoleInfo");
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
    @RequestMapping(value = "/web/modulesRole/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<ModulesRole> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                      @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                      @RequestParam(value = "key", required = false, defaultValue = "") String key,
                                      @RequestParam(value = "moduleId", required = true, defaultValue = "0")
                                              Integer moduleId)
    {
        return modulesRoleService.listPage(page, rows, moduleId, key);
    }

    /**
     * 添加信息
     *
     * @param modulesRole
     * @return
     */
    @RequestMapping(value = "/web/modulesRole/new", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(ModulesRole modulesRole)
    {
        if(!modulesRoleService.checkEnTitle(modulesRole.getEnTitle(), modulesRole.getModuleId()))
        {
            return resultFailsWrapper("当前英文名称已经存在", null);
        }
        return resultBoolWrapper(modulesRoleService.add(modulesRole), "信息创建成功", "信息创建失败", null);
    }

    /**
     * 修改信息
     *
     * @param modulesRole
     * @return
     */
    @RequestMapping(value = "/web/modulesRole/modify", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult modify(ModulesRole modulesRole)
    {
        return resultBoolWrapper(modulesRoleService.modify(modulesRole), "信息修改成功", "信息修改失败", null);
    }

    /**
     * 删除信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/modulesRole/delete", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(modulesRoleService.deleteById(id), "信息删除成功", "信息删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/modulesRole/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getModulesRoleById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", modulesRoleService.findById(id));
    }
}
