package com.nature.jet.service.web;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.web.AdminMapper;
import com.nature.jet.pojo.web.Admin;

import java.util.HashMap;
import java.util.Map;

/**
 * AdminService
 * Author:竺志伟
 * Date:2019-03-22 09:12:53
 */
@Service
public class AdminService
{
    @Autowired
    AdminMapper adminMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<Admin> listPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                adminMapper.list(key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param admin
     * @return
     */
    public boolean add(Admin admin)
    {
        return adminMapper.add(admin) == 1;
    }

    /**
     * 修改数据
     *
     * @param admin
     * @return
     */
    public boolean modify(Admin admin)
    {
        return adminMapper.modify(admin) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return adminMapper.deleteById(id) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(String[] ids)
    {
        adminMapper.deleteByIds(ids);
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public Admin findById(int id)
    {
        return adminMapper.findById(id);
    }

    /**
     * Count by login name and id int.
     *
     * @param loginName the login name
     * @param id        the id
     * @return the int
     * @author:竺志伟
     * @date :2019-03-22 10:31:02
     */
    public int countByLoginNameAndId(String loginName,int id)
    {
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("loginName",loginName);
        paraMap.put("id",id+"");
        return adminMapper.countByLoginNameAndId(paraMap);
    }

    /**
     * Login admin.
     *
     * @param loginName the login name
     * @param loginPass the login pass
     * @return the admin
     * @author:竺志伟
     * @date :2019-03-22 11:11:19
     */
    public Admin login(String loginName,String loginPass)
    {
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("loginName",loginName);
        paraMap.put("loginPass",loginPass);
        return adminMapper.login(paraMap);
    }


    /**
     * Modify password boolean.
     *
     * @param loginPass the login pass
     * @param id        the id
     * @return the boolean
     * @author:竺志伟
     * @date :2019-03-22 15:07:02
     */
    public boolean modifyPassword(String loginPass,int id)
    {
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("loginPass",loginPass);
        paraMap.put("id",id+"");
        return adminMapper.modifyPassword(paraMap) == 1;
    }
}
