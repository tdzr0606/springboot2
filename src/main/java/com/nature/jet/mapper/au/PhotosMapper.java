package com.nature.jet.mapper.au;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.au.Photos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Photos 数据持久化
 * Photos
 * Author:竺志伟
 * Date:2019-04-15 20:55:30
 */
public interface PhotosMapper extends MyMapper<Photos>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:30
     */
    public List<Photos> list(@Param(value = "key") String key);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:30
     */
    public int add(Photos obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:30
     */
    public int modify(Photos obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:30
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 批量删除
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:30
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:30
     */
    public Photos findById(@Param(value = "id") Integer id);

    List<Photos> listPublic();
}
