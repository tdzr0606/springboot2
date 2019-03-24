package com.nature.jet.mapper.au;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.au.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * News 数据持久化
 * News
 * Author:竺志伟
 * Date:2019-03-22 15:36:26
 */
public interface NewsMapper extends MyMapper<News>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 15:36:26
     */
    public List<News> list(@Param(value = "key") String key);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 15:36:26
     */
    public int add(News obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 15:36:26
     */
    public int modify(News obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 15:36:26
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 批量删除
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 15:36:26
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-03-22 15:36:26
     */
    public News findById(@Param(value = "id") Integer id);

    List<News> listPublic();
}
