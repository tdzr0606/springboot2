package com.nature.jet.interceptor;

import com.nature.jet.component.system.SessionSave;
import com.nature.jet.pojo.web.Admin;
import com.nature.jet.utils.Fields;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
@Slf4j
public class MyInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
        String uri = request.getRequestURI();
        if(uri.startsWith("/web/"))
        {
            if(null == request.getSession().getAttribute(Fields.SESSION_ADMIN))
            {
                log.error("----------------------------------------");
                log.error("链接:{}", uri);
                log.error("错误:{}", "登录超时或未登录,请重新登录");
                log.error("----------------------------------------");
                request.getSession().removeAttribute(Fields.SESSION_ADMIN);
                request.setAttribute("errorInfo", "登录超时或未登录,请重新登录");
                request.setAttribute("uri", uri);
                request.getRequestDispatcher("/common/error").forward(request, response);
                return false;
            }
            else
            {
                Admin admin = (Admin) request.getSession().getAttribute(Fields.SESSION_ADMIN);
                String sessionId = request.getSession().getId();
                String mapSessionId = SessionSave.getSessionId(admin.getLoginName());
                //当前用户sessionId 和 存储的sessionId 不一直,下线当前用户
                if(StringUtils.isNotBlank(mapSessionId) && !mapSessionId.equals(sessionId))
                {
                    log.error("----------------------------------------");
                    log.error("链接:{}", uri);
                    log.error("错误:{}:{}", admin.getLoginName(), "账号已经在另外一台设备登录,您被强制下线,请注意账号安全");
                    log.error("----------------------------------------");
                    request.getSession().removeAttribute(Fields.SESSION_ADMIN);
                    request.setAttribute("errorInfo", "当前账号已经在另外一台设备登录,您被强制下线,请注意账号安全");
                    request.setAttribute("uri", uri);
                    request.getRequestDispatcher("/common/error").forward(request, response);
                    return false;
                }
                else if(StringUtils.isBlank(mapSessionId))
                {
                    SessionSave.setSessionInfo(admin.getLoginName(), sessionId);
                    return true;
                }
            }
        }
        return true;
    }
}
