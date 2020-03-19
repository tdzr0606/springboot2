package com.nature.jet.component.system;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * uni2k_mizhe
 * PassUtils
 *
 * @Author: 竺志伟
 * @Date: 2020-01-13 19:51
 */
public class PassUtils
{

    private final static Integer[] randomIndex = new Integer[]{19, 8, 3, 0, 6, 1, 22, 20, 11, 5, 17, 15, 10, 12};


    /**
     * 创建密码
     * Pass word string.
     *
     * @param pass the pass
     * @return the string
     * @author:竺志伟
     * @email :tdzr_0606@126.com
     * @date :2020-01-13 20:05:11
     */
    public static String passWord(String pass)
    {
        pass = DigestUtils.md5Hex(pass.getBytes());
        String[] values = pass.split("");
        String tmp = "";
        for (int i = 0, len = randomIndex.length; i < len; i++)
        {
            tmp = values[randomIndex[i]];
            values[randomIndex[i]] = values[31 - randomIndex[i]];
            values[31 - randomIndex[i]] = tmp;
        }

        StringBuffer sb = new StringBuffer();
        for (String s : values)
        {
            sb.append(s);
        }
        return sb.toString();
    }
}
