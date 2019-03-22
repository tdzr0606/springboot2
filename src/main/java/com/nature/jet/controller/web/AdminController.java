package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.web.AdminService;
import com.nature.jet.pojo.web.Admin;

import javax.servlet.http.HttpServletRequest;

/**
 * Admin控制
 * AdminController
 * Author:竺志伟
 * Date:2019-03-22 09:12:53
 */
@Controller
public class AdminController extends BaseController
{
    @Autowired
    AdminService adminService;
    @Autowired
    HttpServletRequest request;

    /**
     * 进入页面
     *
     * @return
     */
    @RequestMapping(value = "/web/admin/toPage")
    public ModelAndView toPage()
    {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("web/admin");
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
    @RequestMapping(value = "/web/admin/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Admin> findPage(@RequestParam(value = "rows", required = true, defaultValue = "40") Integer rows,
                                @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(value = "key", required = false, defaultValue = "") String key)
    {
        return adminService.listPage(page, rows, key);
    }

    /**
     * 添加信息
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/web/admin/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(Admin admin)
    {
        if(adminService.countByLoginNameAndId(admin.getLoginName(), 0) > 0)
        {
            return resultFailsWrapper("当前用户名已经存在", null);
        }
        return resultBoolWrapper(adminService.add(admin), "信息创建成功", "信息创建失败", null);
    }

    /**
     * 修改信息
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/web/admin/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult modify(Admin admin)
    {
        return resultBoolWrapper(adminService.modify(admin), "信息修改成功", "信息修改失败", null);
    }

    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/web/admin/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "ids", required = true, defaultValue = "0") String ids)
    {
        adminService.deleteByIds(ids.split(","));
        return resultBoolWrapper(true, "信息删除成功", "信息删除失败", null);
    }

    /**
     * 根据id 获取信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/web/admin/info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult getAdminById(@RequestParam(value = "id", required = true, defaultValue = "0") Integer id)
    {
        return resultBoolWrapper(true, "信息装载成功", "信息装载失败", adminService.findById(id));
    }


    /**
     * Password modify common result.
     *
     * @param loginPass  the login pass
     * @param loginPassN the login pass n
     * @return the common result
     * @author:竺志伟
     * @date :2019-03-22 14:49:03
     */
    @RequestMapping(value = "/web/admin/passwordModify")
    @ResponseBody
    public CommonResult passwordModify(@RequestParam(value = "loginPass", required = true, defaultValue = "0") String loginPass,
                                       @RequestParam(value = "loginPassN", required = true, defaultValue = "0")
                                               String loginPassN)
    {
        Admin admin = super.getLoginAdmin(request);
        if(admin.getLoginPass().equals(loginPass))
        {
            return resultBoolWrapper(adminService.modifyPassword(loginPassN, admin.getId()), "密码修改成功,请重新登录", "密码修改失败", null);
        }
        return resultFailsWrapper("原始密码不匹配,密码修改失败", null);
    }
}
