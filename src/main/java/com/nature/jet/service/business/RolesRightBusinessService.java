package com.nature.jet.service.business;

import com.nature.jet.mapper.web.ModulesMapper;
import com.nature.jet.mapper.web.ModulesRoleMapper;
import com.nature.jet.mapper.web.RolesMapper;
import com.nature.jet.mapper.web.RolesRightMapper;
import com.nature.jet.pojo.web.Modules;
import com.nature.jet.pojo.web.ModulesRole;
import com.nature.jet.pojo.web.Roles;
import com.nature.jet.pojo.web.RolesRight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * 角色权限 业务service
 * springboot2
 * RolesRightBusinessService
 *
 * @Author: 竺志伟
 * @Date: 2019-08-04 13:33
 */
@Service
@Slf4j
public class RolesRightBusinessService
{
    @Autowired
    ModulesMapper modulesMapper;
    @Autowired
    ModulesRoleMapper modulesRoleMapper;
    @Autowired
    RolesMapper rolesMapper;
    @Autowired
    RolesRightMapper rolesRightMapper;

    /**
     * Add boolean.
     *
     * @param rolesId     the roles id
     * @param rolesValues the roles values
     * @return the boolean
     * @author:竺志伟
     * @date :2019-08-04 13:34:36
     */
    @Transactional
    public boolean add(int rolesId, String[] rolesValues)
    {
        boolean pd = false;
        try
        {

            Roles roles = rolesMapper.findById(rolesId);

            rolesRightMapper.cleanByRoleId(rolesId);
            log.info("清理原权限数据:{}", roles.getCnName());

            List<Modules> modulesList = modulesMapper.listSub();
            // 模块权限id组合
            List<ModulesRole> modulesRoleList = modulesRoleMapper.listByIds(rolesValues);

            modulesRoleList.stream().forEach(modulesRole ->
            {
                Modules modules = modulesList.stream()
                        .filter(modules1 -> (modules1.getId().intValue() == modulesRole.getModuleId().intValue()))
                        .findFirst().get();

                RolesRight rolesRight = new RolesRight();
                rolesRight.setModuleEnTitle(modules.getEnTitle());
                rolesRight.setModuleTitle(modules.getTitle());
                rolesRight.setModuleRoleTitle(modulesRole.getTitle());
                rolesRight.setModuleRoleEnTitle(modulesRole.getEnTitle());
                rolesRight.setRolesId(rolesId);
                rolesRight.setModuleRoleId(modulesRole.getId());
                rolesRight.setId(0);
                rolesRight.setModuleId(modules.getId());
                rolesRightMapper.add(rolesRight);
                log.info("设置权限:{},{}", modules.getTitle(), modulesRole.getTitle());
            });
            pd = true;
            log.info("权限设置完成");
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
