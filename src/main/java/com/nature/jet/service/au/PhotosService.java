package com.nature.jet.service.au;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.au.PhotosMapper;
import com.nature.jet.pojo.au.Photos;

/**
 * PhotosService
 * Author:竺志伟
 * Date:2019-04-15 20:55:30
 */
@Service
public class PhotosService
{
    @Autowired
    PhotosMapper photosMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<Photos> listPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                photosMapper.list(key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param photos
     * @return
     */
    public boolean add(Photos photos)
    {
        return photosMapper.add(photos) == 1;
    }

    /**
     * 修改数据
     *
     * @param photos
     * @return
     */
    public boolean modify(Photos photos)
    {
        return photosMapper.modify(photos) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return photosMapper.deleteById(id) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(String[] ids)
    {
        photosMapper.deleteByIds(ids);
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public Photos findById(int id)
    {
        return photosMapper.findById(id);
    }


    public Page<Photos> listPublicPage(Integer nowPage, Integer pageSize)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                photosMapper.listPublic();
            }
        }));
    }
}
