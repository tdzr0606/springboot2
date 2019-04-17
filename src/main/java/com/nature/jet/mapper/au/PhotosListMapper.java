package com.nature.jet.mapper.au;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.au.PhotosList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * PhotosList 数据持久化
 * PhotosList
 * Author:竺志伟
 * Date:2019-04-15 20:55:54
 */
public interface PhotosListMapper extends MyMapper<PhotosList>
{
    /**
     * 查询所有数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:54
     */
    public List<PhotosList> list(Map<String, String> map);

    /**
     * 添加新数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:54
     */
    public int add(PhotosList obj);

    /**
     * 更新数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:54
     */
    public int modify(PhotosList obj);

    /**
     * 删除数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:54
     */
    public int deleteById(@Param(value = "id") Integer id);

    /**
     * 批量删除
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:54
     */
    public void deleteByIds(@Param(value = "ids") String[] ids);

    /**
     * 根据id查询数据
     * <p>
     * Author:竺志伟
     * Date:2019-04-15 20:55:54
     */
    public PhotosList findById(@Param(value = "id") Integer id);

    int countByPhotosId(@Param(value = "photosId") Integer photosId);

    List<PhotosList> listByPhotosId(@Param(value = "photosId") Integer photosId);
}
