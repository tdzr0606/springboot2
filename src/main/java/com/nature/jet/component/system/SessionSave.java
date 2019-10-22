package com.nature.jet.component.system;

import java.util.HashMap;
import java.util.Map;

/**
 * 储存 登录用户和session关联关系
 * uni2k
 * SessionMap
 *
 * @Author: 竺志伟
 * @Date: 2019-03-28 10:20
 */
public class SessionSave
{
    private static Map<String, String> sessionMap = new HashMap<>();

    /**
     * Sets session info.
     *
     * @param loginName the login name
     * @param sessionId the session id
     * @author:竺志伟
     * @date :2019-03-28 10:26:08
     */
    public static synchronized void setSessionInfo(String loginName, String sessionId)
    {
        sessionMap.remove(loginName);
        sessionMap.put(loginName, sessionId);
    }


    /**
     * Gets session id.
     *
     * @param loginName the login name
     * @return the session id
     * @author:竺志伟
     * @date :2019-03-28 10:26:11
     */
    public static String getSessionId(String loginName)
    {
        return sessionMap.get(loginName);
    }
}
