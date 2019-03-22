package com.nature.jet.component.system;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class Page<T>
{
    private int code;
    private String msg;
    private int page;
    private int limit;
    private int count;
    private int totalPage;
    private List<T> data;

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getTotalPage()
    {
        totalPage = count / limit;
        if(count % limit != 0)
        {
            totalPage++;
        }
        return totalPage;
    }

    public Page()
    {
        this.code = 0;
        this.msg = "";
        this.page = 1;
        this.limit = 1;
        this.count = 1;
        this.totalPage = 0;
        this.data = null;
    }


    public Page(int nowPage, int count, int totalPage, int pageSize, List<T> dataList)
    {
        this.code = 0;
        this.msg = "";
        this.page = nowPage;
        this.limit = pageSize;
        this.count = count;
        this.totalPage = totalPage;
        this.data = dataList;
    }


    /**
     * 针对 mybatis 分页 转换
     * Instantiates a new Page.
     *
     * @param pageInfo the page info
     * @author 竺志伟
     * @date 2019 -03-19 22:32:21
     */
    public Page(PageInfo<T> pageInfo)
    {
        this.code = 0;
        this.msg = "";
        this.data = pageInfo.getList();
        this.page = pageInfo.getPageNum();
        this.limit = pageInfo.getPageSize();
        this.count = (int) pageInfo.getTotal();
        this.totalPage = pageInfo.getPages();
    }

}