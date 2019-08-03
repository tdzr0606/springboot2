package com.nature.jet.service.business;

import com.nature.jet.mapper.web.ModulesMapper;
import com.nature.jet.mapper.web.ModulesRoleMapper;
import com.nature.jet.pojo.web.Modules;
import com.nature.jet.pojo.web.ModulesRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * springboot2
 * ModuleBusinessService
 *
 * @Author: 竺志伟
 * @Date: 2019-08-03 15:42
 */
@Service
public class ModuleBusinessService
{
    @Autowired
    ModulesMapper modulesMapper;
    @Autowired
    ModulesRoleMapper modulesRoleMapper;

    /**
     * Add modules boolean.
     *
     * @param modules the modules
     * @return the boolean
     * @author:竺志伟
     * @date :2019-08-03 15:47:12
     */
    @Transactional
    public boolean add(Modules modules)
    {
        try
        {
            modulesMapper.add(modules);
            int moduleId = modules.getId();

            ModulesRole role = new ModulesRole();
            role.setNote("");
            role.setEnTitle("new");
            role.setIsPublic(true);
            role.setId(0);
            role.setTitle("新建");
            role.setModuleId(moduleId);
            modulesRoleMapper.add(role);

            role = new ModulesRole();
            role.setNote("");
            role.setEnTitle("mod");
            role.setIsPublic(true);
            role.setId(0);
            role.setTitle("修改");
            role.setModuleId(moduleId);
            modulesRoleMapper.add(role);

            role = new ModulesRole();
            role.setNote("");
            role.setEnTitle("del");
            role.setIsPublic(true);
            role.setId(0);
            role.setTitle("删除");
            role.setModuleId(moduleId);
            modulesRoleMapper.add(role);

            return true;
        }
        catch(Exception e)
        {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
