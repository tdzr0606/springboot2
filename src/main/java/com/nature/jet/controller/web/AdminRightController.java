package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import com.nature.jet.service.business.AdminRightBusinessService;
import com.nature.jet.service.web.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.web.AdminRightService;
import com.nature.jet.pojo.web.AdminRight;

/**
 * AdminRight控制
 * AdminRightController
 * Author:竺志伟
 * Date:2019-08-04 16:14:43
 */
@Controller
public class AdminRightController extends BaseController
{
    @Autowired
    AdminRightService adminRightService;
    @Autowired
    RolesService rolesService;
    @Autowired
    AdminRightBusinessService adminRightBusinessService;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/adminRight/toPage")
    public ModelAndView toPage(@RequestParam(value = "adminId", required = true, defaultValue = "0") Integer adminId)
    {
        modelAndView = new ModelAndView();
        modelAndView.addObject("adminId", adminId);
        modelAndView.addObject("roles", rolesService.listPublic());
        modelAndView.setViewName("web/popular/adminRightInfo");
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
    @RequestMapping(value = "/web/adminRight/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<AdminRight> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                     @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                     @RequestParam(value = "key", required = false, defaultValue = "") String key,
                                     @RequestParam(value = "adminId", required = true, defaultValue = "0")
                                             Integer adminId)
    {
        return adminRightService.listPage(page, rows, adminId, key);
    }

    /**
     * 添加信息
     *
     * @param adminId the admin id
     * @param roleId  the role id
     * @return common result
     * @author:竺志伟
     * @date :2019-08-04 16:44:09
     */
    @RequestMapping(value = "/web/adminRight/new", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(@RequestParam(value = "adminId", required = true, defaultValue = "0") Integer adminId,
                            @RequestParam(value = "roleId", required = true, defaultValue = "0") Integer roleId)
    {
        return resultBoolWrapper(adminRightBusinessService.add(adminId, roleId), "账号权限设置完成", "账号权限设置失败", null);
    }


    /**
     * 删除信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/adminRight/delete", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(adminRightBusinessService.delete(id), "账号权限设删除成功", "账号权限设删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/adminRight/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getAdminRightById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", adminRightService.findById(id));
    }
}
