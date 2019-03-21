package com.nature.jet.interceptor;

import com.nature.jet.utils.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * springboot2
 * MyInterceptor
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 22:43
 */
public class MyInterceptor implements HandlerInterceptor
{
    private static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String uri = request.getRequestURI();
        logger.info("访问路径:{}", uri);
        if(uri.startsWith("/web/"))
        {
            if(null == request.getSession().getAttribute(Fields.SESSION_ADMIN))
            {
                request.getSession().removeAttribute(Fields.SESSION_ADMIN);
                request.setAttribute("errorInfo", "登录超时或未登录,请重新登录");
                request.setAttribute("uri", request.getRequestURI());
                request.getRequestDispatcher("/common/error").forward(request, response);
                return false;
            }
        }
        return true;
    }
}
