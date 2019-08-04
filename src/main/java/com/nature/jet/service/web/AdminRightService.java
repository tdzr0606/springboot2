package com.nature.jet.service.web;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.web.AdminRightMapper;
import com.nature.jet.pojo.web.AdminRight;

import java.util.List;

/**
 * AdminRightService
 * Author:竺志伟
 * Date:2019-08-04 16:14:43
 */
@Service
public class AdminRightService
{
    @Autowired
    AdminRightMapper adminRightMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<AdminRight> listPage(Integer nowPage, Integer pageSize, Integer adminId, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                adminRightMapper.list(adminId, key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param adminRight
     * @return
     */
    public boolean add(AdminRight adminRight)
    {
        return adminRightMapper.add(adminRight) == 1;
    }

    /**
     * 修改数据
     *
     * @param adminRight
     * @return
     */
    public boolean modify(AdminRight adminRight)
    {
        return adminRightMapper.modify(adminRight) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return adminRightMapper.deleteById(id) == 1;
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public AdminRight findById(int id)
    {
        return adminRightMapper.findById(id);
    }

    public List<AdminRight> listByAdminId(int adminId)
    {
        return adminRightMapper.list(adminId, null);
    }
}
