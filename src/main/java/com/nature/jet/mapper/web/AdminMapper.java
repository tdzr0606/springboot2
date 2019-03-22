package com.nature.jet.mapper.web;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.web.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Admin 数据持久化
 * Admin
 * Author:竺志伟
 * Date:2019-03-22 09:12:53
 */
public interface AdminMapper extends MyMapper<Admin>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 09:12:53
     */
    public List<Admin> list(@Param(value = "key") String key);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 09:12:53
     */
    public int add(Admin obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 09:12:53
     */
    public int modify(Admin obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 09:12:53
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 批量删除
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 09:12:53
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 09:12:53
     */
    public Admin findById(@Param(value = "id") Integer id);

    int countByLoginNameAndId(Map<String, String> paraMap);

    Admin login(Map<String, String> paraMap);

    int modifyPassword(Map<String,String> paraMap);
}
