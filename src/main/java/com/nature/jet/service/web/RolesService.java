package com.nature.jet.service.web;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.web.RolesMapper;
import com.nature.jet.pojo.web.Roles;

/**
 * RolesService
 * Author:竺志伟
 * Date:2019-07-28 12:05:00
 */
@Service
public class RolesService
{
    @Autowired
    RolesMapper rolesMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<Roles> listPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                rolesMapper.list(key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param roles
     * @return
     */
    public boolean add(Roles roles)
    {
        return rolesMapper.add(roles) == 1;
    }

    /**
     * 修改数据
     *
     * @param roles
     * @return
     */
    public boolean modify(Roles roles)
    {
        return rolesMapper.modify(roles) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return rolesMapper.deleteById(id) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(String[] ids)
    {
        rolesMapper.deleteByIds(ids);
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public Roles findById(int id)
    {
        return rolesMapper.findById(id);
    }

    public boolean checkEnName(String enName)
    {
        return rolesMapper.checkEnName(enName) == 0;
    }
}
