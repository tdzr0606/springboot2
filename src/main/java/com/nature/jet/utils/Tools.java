package com.nature.jet.utils;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        if(StringUtils.isBlank(pattern))
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
        if(StringUtils.isBlank(pattern))
        {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.now());
    }
}
