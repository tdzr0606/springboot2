package com.nature.jet.component.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 防止攻击filter
 * km_leader
 * XssCodeFilter
 *
 * @author:竺志伟
 * @date :2018-01-09 15:27:26
 * @Author: 竺志伟
 * @Date: 2018 -01-09 15:25
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "xss")
@Slf4j
public class XssCodeFilter implements Filter
{
    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        log.info("xss过滤:{}",((HttpServletRequest) servletRequest).getRequestURI());
        filterChain.doFilter(new XssCodeWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy()
    {
        this.filterConfig = null;
    }
}
