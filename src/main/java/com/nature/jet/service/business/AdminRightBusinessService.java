package com.nature.jet.service.business;

import com.nature.jet.mapper.web.AdminMapper;
import com.nature.jet.mapper.web.AdminRightMapper;
import com.nature.jet.mapper.web.RolesMapper;
import com.nature.jet.mapper.web.RolesRightMapper;
import com.nature.jet.pojo.web.Admin;
import com.nature.jet.pojo.web.AdminRight;
import com.nature.jet.pojo.web.Roles;
import com.nature.jet.pojo.web.RolesRight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * springboot2
 * AdminRightBusinessService
 *
 * @Author: 竺志伟
 * @Date: 2019-08-04 16:46
 */
@Service
@Slf4j
public class AdminRightBusinessService
{
    @Autowired
    AdminRightMapper adminRightMapper;
    @Autowired
    RolesRightMapper rolesRightMapper;
    @Autowired
    RolesMapper rolesMapper;
    @Autowired
    AdminMapper adminMapper;

    /**
     * Add boolean.
     *
     * @return the boolean
     * @author:竺志伟
     * @date :2019-08-04 16:46:54
     */
    @Transactional
    public boolean add(int adminId, int roleId)
    {
        boolean pd = false;
        try
        {
            Admin admin = adminMapper.findById(adminId);
            Roles roles = rolesMapper.findById(roleId);
            log.info("账号权限设定,{},{}", admin.getUserName(), roles.getCnName());
            List<RolesRight> rolesRightList = rolesRightMapper.list(roleId, null);

            final StringBuffer sb = new StringBuffer();
            rolesRightList.stream().forEach(rolesRight ->
            {
                sb.append(rolesRight.getModuleEnTitle()).append(":").append(rolesRight.getModuleRoleEnTitle())
                        .append(",");
            });
            sb.deleteCharAt(sb.length() - 1);

            AdminRight adminRight = new AdminRight();
            adminRight.setRoleEnTitle(roles.getEnName());
            adminRight.setRightText(sb.toString());
            adminRight.setRoleTitle(roles.getCnName());
            adminRight.setRoleId(roleId);
            adminRight.setAdminId(adminId);
            adminRight.setId(0);
            adminRightMapper.add(adminRight);
            pd = true;
            log.info("账号权限设定完成");
        }
        catch(Exception e)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        finally
        {
            return pd;
        }
    }
}
