package com.nature.jet.service.web;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.web.ModulesRoleMapper;
import com.nature.jet.pojo.web.ModulesRole;

/**
 * ModulesRoleService
 * Author:竺志伟
 * Date:2019-08-03 14:42:17
 */
@Service
public class ModulesRoleService
{
    @Autowired
    ModulesRoleMapper modulesRoleMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<ModulesRole> listPage(Integer nowPage, Integer pageSize, Integer moduleId, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                modulesRoleMapper.list(moduleId, key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param modulesRole
     * @return
     */
    public boolean add(ModulesRole modulesRole)
    {
        return modulesRoleMapper.add(modulesRole) == 1;
    }

    /**
     * 修改数据
     *
     * @param modulesRole
     * @return
     */
    public boolean modify(ModulesRole modulesRole)
    {
        return modulesRoleMapper.modify(modulesRole) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return modulesRoleMapper.deleteById(id) == 1;
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public ModulesRole findById(int id)
    {
        return modulesRoleMapper.findById(id);
    }

    public boolean checkEnTitle(String enTitle,int moduleId)
    {
        return modulesRoleMapper.checkEnTitle(enTitle,moduleId) == 0;
    }
}
