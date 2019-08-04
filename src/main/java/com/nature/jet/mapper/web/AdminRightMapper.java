package com.nature.jet.mapper.web;

import com.nature.jet.mybatis.config.MyMapper;
import com.nature.jet.pojo.web.AdminRight;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 
 * AdminRight 数据持久化
 * AdminRight
 * Author:竺志伟
 * Date:2019-08-04 16:14:43
 */
@Repository
public interface AdminRightMapper extends MyMapper<AdminRight>
{
   /**
    * 查询所有数据
    * 
    * Author:竺志伟
    * Date:2019-08-04 16:14:43
    */
    public List<AdminRight> list(@Param(value = "adminId") Integer adminId,@Param(value = "key") String key);

   /**
    * 添加新数据
    * 
    * Author:竺志伟
    * Date:2019-08-04 16:14:43
    */
    public int add(AdminRight obj);

   /**
    * 更新数据
    * 
    * Author:竺志伟
    * Date:2019-08-04 16:14:43
    */
    public int modify(AdminRight obj);

   /**
    * 删除数据
    * 
    * Author:竺志伟
    * Date:2019-08-04 16:14:43
    */
    public int deleteById(@Param(value = "id") Integer id);

   /**
    * 根据id查询数据
    * 
    * Author:竺志伟
    * Date:2019-08-04 16:14:43
    */
    public AdminRight findById(@Param(value = "id") Integer id);
}
