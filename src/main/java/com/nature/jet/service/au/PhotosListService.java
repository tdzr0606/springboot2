package com.nature.jet.service.au;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.au.PhotosListMapper;
import com.nature.jet.pojo.au.PhotosList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PhotosListService
 * Author:竺志伟
 * Date:2019-04-15 20:55:54
 */
@Service
public class PhotosListService
{
    @Autowired
    PhotosListMapper photosListMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<PhotosList> listPage(Integer nowPage, Integer pageSize, String key, Integer photosId)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                Map<String, String> paraMap = new HashMap<>();
                paraMap.put("key", key);
                paraMap.put("photosId", photosId.toString());
                photosListMapper.list(paraMap);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param photosList
     * @return
     */
    public boolean add(PhotosList photosList)
    {
        return photosListMapper.add(photosList) == 1;
    }

    /**
     * 修改数据
     *
     * @param photosList
     * @return
     */
    public boolean modify(PhotosList photosList)
    {
        return photosListMapper.modify(photosList) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return photosListMapper.deleteById(id) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(String[] ids)
    {
        photosListMapper.deleteByIds(ids);
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public PhotosList findById(int id)
    {
        return photosListMapper.findById(id);
    }


    public int countByPhotosId(int photosId)
    {
        return photosListMapper.countByPhotosId(photosId);
    }

    public List<PhotosList> listByPhotosId(int photosId)
    {
        return photosListMapper.listByPhotosId(photosId);
    }
}
