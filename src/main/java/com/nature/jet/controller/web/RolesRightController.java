package com.nature.jet.controller.web;

import com.nature.jet.controller.system.BaseController;
import com.nature.jet.component.system.CommonResult;
import com.nature.jet.component.system.Page;
import com.nature.jet.service.business.RolesRightBusinessService;
import com.nature.jet.service.web.ModulesRoleService;
import com.nature.jet.service.web.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nature.jet.service.web.RolesRightService;
import com.nature.jet.pojo.web.RolesRight;

import java.util.HashMap;
import java.util.Map;

/**
 * RolesRight控制
 * RolesRightController
 * Author:竺志伟
 * Date:2019-08-04 11:53:36
 */
@Controller
public class RolesRightController extends BaseController
{
    @Autowired
    RolesRightService rolesRightService;
    @Autowired
    ModulesService modulesService;
    @Autowired
    ModulesRoleService modulesRoleService;
    @Autowired
    RolesRightBusinessService rolesRightBusinessService;

    /**
     * 角色权限初始化
     *
     * @return
     */
    @RequestMapping(value = "/web/rolesRight/init", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult init(@RequestParam(value = "roleId", required = true, defaultValue = "0") Integer roleId)
    {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("parentModules", modulesService.listPublicParent());
        dataMap.put("subModules", modulesService.listPublicSub());
        dataMap.put("moduleRoles", modulesRoleService.listForPublicModule());
        dataMap.put("roleRights", rolesRightService.listByRoleId(roleId));
        return resultSuccessWrapper("", dataMap);
    }


    /**
     * 权限设置
     *
     * @param rolesId    the roles id
     * @param rolesValue the roles value
     * @return common result
     * @author:竺志伟
     * @date :2019-08-04 13:33:09
     */
    @RequestMapping(value = "/web/rolesRight/set", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResult add(@RequestParam(value = "rolesId", required = true, defaultValue = "0") Integer rolesId,
                            @RequestParam(value = "rolesValue", required = true, defaultValue = "") String rolesValue)
    {
        return resultBoolWrapper(rolesRightBusinessService.add(rolesId, rolesValue.split(",")), "权限设置成功", "权限设置失败",
                null);
    }


}
