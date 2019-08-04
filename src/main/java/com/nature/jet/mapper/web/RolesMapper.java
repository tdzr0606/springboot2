package com.nature.jet.mapper.web;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.web.Roles;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Roles 数据持久化
 * Roles
 * Author:竺志伟
 * Date:2019-07-28 12:05:00
 */
@Repository
public interface RolesMapper extends MyMapper<Roles>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 12:05:00
     */
    public List<Roles> list(@Param(value = "key") String key);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 12:05:00
     */
    public int add(Roles obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 12:05:00
     */
    public int modify(Roles obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 12:05:00
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 批量删除
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 12:05:00
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 12:05:00
     */
    public Roles findById(@Param(value = "id") Integer id);

    int checkEnName(@Param(value = "enName") String enName);

    List<Roles> listPublic();
}
