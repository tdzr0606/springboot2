package com.nature.jet.service.web;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.web.RolesRightMapper;
import com.nature.jet.pojo.web.RolesRight;

import java.util.List;

/**
 * RolesRightService
 * Author:竺志伟
 * Date:2019-08-04 11:53:36
 */
@Service
public class RolesRightService
{
    @Autowired
    RolesRightMapper rolesRightMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<RolesRight> listPage(Integer nowPage, Integer pageSize, Integer roleId, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                rolesRightMapper.list(roleId, key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param rolesRight
     * @return
     */
    public boolean add(RolesRight rolesRight)
    {
        return rolesRightMapper.add(rolesRight) == 1;
    }

    /**
     * 修改数据
     *
     * @param rolesRight
     * @return
     */
    public boolean modify(RolesRight rolesRight)
    {
        return rolesRightMapper.modify(rolesRight) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return rolesRightMapper.deleteById(id) == 1;
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public RolesRight findById(int id)
    {
        return rolesRightMapper.findById(id);
    }

    public void cleanByRoleId(int roleId)
    {
        rolesRightMapper.cleanByRoleId(roleId);
    }

    public List<RolesRight> listByRoleId(int roleId)
    {
        return rolesRightMapper.list(roleId, null);
    }
}
