package com.nature.jet.mapper.web;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.web.RolesRight;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RolesRight 数据持久化
 * RolesRight
 * Author:竺志伟
 * Date:2019-08-04 11:53:36
 */
@Repository
public interface RolesRightMapper extends MyMapper<RolesRight>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-04 11:53:36
     */
    public List<RolesRight> list(@Param(value = "roleId") Integer roleId, @Param(value = "key") String key);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-04 11:53:36
     */
    public int add(RolesRight obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-04 11:53:36
     */
    public int modify(RolesRight obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-04 11:53:36
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-04 11:53:36
     */
    public RolesRight findById(@Param(value = "id") Integer id);

    int cleanByRoleId(@Param(value = "roleId") Integer roleId);
}
