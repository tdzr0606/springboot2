package com.nature.jet.service.au;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.nature.jet.component.system.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nature.jet.mapper.au.NewsMapper;
import com.nature.jet.pojo.au.News;

/**
 * NewsService
 * Author:竺志伟
 * Date:2019-03-22 15:36:26
 */
@Service
public class NewsService
{
    @Autowired
    NewsMapper newsMapper;

    /**
     * 获取分页数据
     *
     * @param nowPage
     * @param pageSize
     * @param key
     * @return
     */
    public Page<News> listPage(Integer nowPage, Integer pageSize, String key)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                newsMapper.list(key);
            }
        }));
    }

    /**
     * 创建数据
     *
     * @param news
     * @return
     */
    public boolean add(News news)
    {
        return newsMapper.add(news) == 1;
    }

    /**
     * 修改数据
     *
     * @param news
     * @return
     */
    public boolean modify(News news)
    {
        return newsMapper.modify(news) == 1;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteById(Integer id)
    {
        return newsMapper.deleteById(id) == 1;
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    public void deleteByIds(String[] ids)
    {
        newsMapper.deleteByIds(ids);
    }

    /**
     * 获取单一数据
     *
     * @param id
     * @return
     */
    public News findById(int id)
    {
        return newsMapper.findById(id);
    }


    /**
     * List public page page.
     *
     * @param nowPage  the now page
     * @param pageSize the page size
     * @return the page
     * @author:竺志伟
     * @date :2019-03-23 15:25:02
     */
    public Page<News> listPublicPage(Integer nowPage, Integer pageSize)
    {
        return new Page<>(PageHelper.startPage(nowPage, pageSize).doSelectPageInfo(new ISelect()
        {
            @Override
            public void doSelect()
            {
                newsMapper.listPublic();
            }
        }));
    }
}
