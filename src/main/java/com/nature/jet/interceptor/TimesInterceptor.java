package com.nature.jet.interceptor;

import com.nature.jet.component.system.CommonResult;
import com.nature.jet.utils.JsonUtils;
import com.nature.jet.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * 时间戳 拦截器
 * uni2k_mizhe
 * TimesInterceptor
 *
 * @Author: 竺志伟
 * @Date: 2019-11-18 08:38
 */
@Slf4j
public class TimesInterceptor implements HandlerInterceptor
{
    @Autowired
    CommonResult result;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap.containsKey("time"))
        {
            String timeV = ((String[]) paramMap.get("time"))[0];
            if (StringUtils.isNotBlank(timeV) && StringUtils.isNumeric(timeV))
            {
                long timeVl = Long.valueOf(timeV);
                long nowl = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                if (nowl - timeVl > 5000)
                {
                    log.error("请求超时:{},{}", Tools.getRequestIp(request), request.getRequestURI());
                    result.setCode(CommonResult.FAILS);
                    result.setMsg("请求时间超时");
                    response.getWriter().print(JsonUtils.toJson(result, null));
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }
}
