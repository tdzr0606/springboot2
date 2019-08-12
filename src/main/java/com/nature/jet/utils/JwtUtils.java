package com.nature.jet.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * springboot2
 * JwtUtils
 *
 * @Author: 竺志伟
 * @Date: 2019-08-12 10:30
 */
@Slf4j
public class JwtUtils
{
    private String secret;

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    /**
     * 创建 token
     * Create token string.
     *
     * @return the string
     * @author:竺志伟
     * @date :2019-08-12 10:48:55
     */
    public String createToken(String uri, String desc, int expirSeconds)
    {
        LocalDateTime now = LocalDateTime.now();
        Claims claims = Jwts.claims(); //存放需要传递的内容
        claims.put("createTime", now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssS")));
        claims.put("uri", uri);
        claims.put("desc", desc);
        expirSeconds = (0 == expirSeconds) ? 30 : expirSeconds; //默认1分钟
        Date expirDate = Tools.formatLocalDateTime2Date(now.plusMinutes(expirSeconds)); //有效期 分钟
        log.info("创建token,{},{},{}", now, uri, desc);
        return Jwts.builder().setClaims(claims).setExpiration(expirDate).signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 解析token
     * Parse token.
     *
     * @param token the token
     * @author:竺志伟
     * @date :2019-08-12 10:53:01
     */
    public Map<String, String> parseToken(String token)
    {
        Map<String, String> dataMap = new HashMap<>();
        try
        {
            Jws<Claims> jws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            String signature = jws.getSignature();
            Map<String, String> header = jws.getHeader();
            Claims claims = jws.getBody();
            log.info("token解析数据:{}", claims.toString());
            dataMap.put("code", "0");
            dataMap.put("msg", "解析成功");
            dataMap.put("data", claims.get("uri").toString());
        }
        catch(JwtException e)
        {
            log.error("token解析错误", e);
            dataMap.put("code", "1");
            dataMap.put("msg", "认证失败");
        }
        finally
        {
            return dataMap;
        }
    }


    public static void main(String[] args)
    {
        JwtUtils jwtUtils = new JwtUtils();
        jwtUtils.setSecret("123asdfjoiewXASD@sadfe");
        //        String token = jwtUtils.createToken("/api/news/top10", "获取前10条新闻信息", 10);
        String token = "eyJhbGciOiJIUzUxMiJ9" +
                ".eyJjcmVhdGVUaW1lIjoiMjAxOTA4MTIxMzEzNDE5IiwidXJpIjoiL2FwaS9uZXdzL3RvcDEwIiwiZGVzYyI6IuiOt" +
                "-WPluWJjTEw5p2h5paw6Ze75L-h5oGvIiwiZXhwIjoxNTY1NTg3NDIxfQ" +
                ".9N0_8Sv-i3t03VaR5uuFymeZEl8xP3wDyhFbs4mKF0vNRCmqi5E08u3wbDElvOUhMBAG9ISO923_9allJRhosg";
        System.out.println(token);
        Map<String, String> dataMap = jwtUtils.parseToken(token);
        System.out.println(dataMap.get("code"));
        System.out.println(dataMap.get("msg"));
        System.out.println(dataMap.get("data"));
    }
}
