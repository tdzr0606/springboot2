package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.web.RolesService;
import com.nature.jet.pojo.web.Roles;

/**
 * Roles控制
 * RolesController
 * Author:竺志伟
 * Date:2019-07-28 12:05:00
 */
@Controller
public class RolesController extends BaseController
{
    @Autowired
    RolesService rolesService;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/roles/toPage")
    @RequiresPermissions(value = "roles:show")
    public ModelAndView toPage()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("web/rolesInfo");
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
    @RequestMapping(value = "/web/roles/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Roles> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return rolesService.listPage(page, rows, key);
    }

    /**
     * 添加信息
     *
     * @param roles
     * @return
     */
    @RequestMapping(value = "/web/roles/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(Roles roles)
    {
        if(!rolesService.checkEnName(roles.getEnName()))
        {
            return resultFailsWrapper("当前角色英文名已经存在", null);
        }
        return resultBoolWrapper(rolesService.add(roles), "信息创建成功", "信息创建失败", null);
    }

    /**
     * 修改信息
     *
     * @param roles
     * @return
     */
    @RequestMapping(value = "/web/roles/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult modify(Roles roles)
    {
        return resultBoolWrapper(rolesService.modify(roles), "信息修改成功", "信息修改失败", null);
    }

    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/web/roles/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", required = true, defaultValue = "0") String ids)
    {
        rolesService.deleteByIds(ids.split(","));
        return resultBoolWrapper(true, "信息删除成功", "信息删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/roles/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getRolesById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", rolesService.findById(id));
    }
}
