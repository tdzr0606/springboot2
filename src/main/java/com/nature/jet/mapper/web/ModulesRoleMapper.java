package com.nature.jet.mapper.web;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.web.ModulesRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ModulesRole 数据持久化
 * ModulesRole
 * Author:竺志伟
 * Date:2019-08-03 14:42:17
 */
@Repository
public interface ModulesRoleMapper extends MyMapper<ModulesRole>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-03 14:42:17
     */
    public List<ModulesRole> list(@Param(value = "moduleId") Integer moduleId, @Param(value = "key") String key);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-03 14:42:17
     */
    public int add(ModulesRole obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-03 14:42:17
     */
    public int modify(ModulesRole obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-03 14:42:17
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-08-03 14:42:17
     */
    public ModulesRole findById(@Param(value = "id") Integer id);

    int checkEnTitle(@Param(value = "enTitle") String enTitle,@Param(value = "moduleId") Integer moduleId);

    List<ModulesRole> listForPublicModule();
}
