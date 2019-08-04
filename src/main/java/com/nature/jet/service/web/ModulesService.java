package com.nature.jet.service.web;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.web.ModulesMapper;
import com.nature.jet.pojo.web.Modules;

import java.util.List;

/**
 * ModulesService
 * Author:竺志伟
 * Date:2019-07-28 13:40:31
 */
@Service
public class ModulesService
{
    @Autowired
    ModulesMapper modulesMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<Modules> listPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                modulesMapper.list(key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param modules
     * @return
     */
    public boolean add(Modules modules)
    {
        return modulesMapper.add(modules) == 1;
    }

    /**
     * 修改数据
     *
     * @param modules
     * @return
     */
    public boolean modify(Modules modules)
    {
        return modulesMapper.modify(modules) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return modulesMapper.deleteById(id) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(String[] ids)
    {
        modulesMapper.deleteByIds(ids);
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public Modules findById(int id)
    {
        return modulesMapper.findById(id);
    }


    public List<Modules> listPublicParent()
    {
        return modulesMapper.listPublicParent();
    }

    public List<Modules> listPublicSub()
    {
        return modulesMapper.listPublicSub();
    }

    public List<Modules> listParent()
    {
        return modulesMapper.listParent();
    }

    public List<Modules> listSub()
    {
        return modulesMapper.listSub();
    }

    public boolean useById(int id)
    {
        return modulesMapper.useById(id) > 0;
    }

    public boolean useByParentId(int id, int isPublic)
    {
        return modulesMapper.useByParentId(id, isPublic) > 0;
    }


    public boolean checkEnTitle(String enTitle)
    {
        return modulesMapper.checkEnTitle(enTitle) == 0;
    }

}
