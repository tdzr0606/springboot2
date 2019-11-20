package com.nature.jet.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * springboot2
 * Tools
 *
 * @Author: 竺志伟
 * @Date: 2019-03-22 20:45
 */
public class Tools
{

    /**
     * Gets now date.
     *
     * @param pattern the pattern
     * @return the now date
     * @author:竺志伟
     * @date :2019-03-22 20:48:44
     */
    public static String getNowDate(String pattern)
    {
        if (StringUtils.isBlank(pattern))
        {
            pattern = "yyyy-MM-dd";
        }
        return DateTimeFormatter.ofPattern(pattern).format(LocalDate.now());
    }


    /**
     * Gets now date time.
     *
     * @param pattern the pattern
     * @return the now date time
     * @author:竺志伟
     * @date :2019-03-22 20:48:46
     */
    public static String getNowDateTime(String pattern)
    {
        if (StringUtils.isBlank(pattern))
        {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.now());
    }


    /**
     * 格式化 localdate 2 date
     * Format local date to date date.
     *
     * @param localDate the local date
     * @return the date
     * @author:竺志伟
     * @date :2019-08-12 10:42:21
     */
    public static Date formatLocalDate2Date(LocalDate localDate)
    {
        localDate = (null == localDate) ? LocalDate.now() : localDate;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 格式化 localdatetime 2 date
     * Format local date time 2 date date.
     *
     * @param localDateTime the local date time
     * @return the date
     * @author:竺志伟
     * @date :2019-08-12 10:44:34
     */
    public static Date formatLocalDateTime2Date(LocalDateTime localDateTime)
    {
        localDateTime = (null == localDateTime) ? LocalDateTime.now() : localDateTime;
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Gets request ip.
     *
     * @param request the request
     * @return the request ip
     * @author:竺志伟
     * @email :tdzr_0606@126.com
     * @date :2019-11-20 08:29:11
     */
    public static String getRequestIp(HttpServletRequest request)
    {
        String ip = null;
        if (request.getHeader("x-forwarded-for") == null)
        {
            ip = request.getRemoteAddr();
        }
        else
        {
            ip = request.getHeader("x-forwarded-for");
        }
        return ip;
    }
}
