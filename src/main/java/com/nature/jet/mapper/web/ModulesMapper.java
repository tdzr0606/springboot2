package com.nature.jet.mapper.web;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.web.Modules;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Modules 数据持久化
 * Modules
 * Author:竺志伟
 * Date:2019-07-28 13:40:31
 */
@Repository
public interface ModulesMapper extends MyMapper<Modules>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 13:40:31
     */
    public List<Modules> list(@Param(value = "key") String key);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 13:40:31
     */
    public int add(Modules obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 13:40:31
     */
    public int modify(Modules obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 13:40:31
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 批量删除
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 13:40:31
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-07-28 13:40:31
     */
    public Modules findById(@Param(value = "id") Integer id);

    List<Modules> listPublicParent();

    List<Modules> listParent();

    List<Modules> listSub();

    int useById(@Param(value = "id") Integer id);

    int useByParentId(@Param(value = "id") Integer id, @Param(value = "isPublic") Integer isPublic);

    int checkEnTitle(@Param(value = "enTitle") String enTitle);
}
