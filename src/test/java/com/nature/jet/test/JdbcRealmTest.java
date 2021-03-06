package com.nature.jet.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * springboot2
 * JdbcRealmTest
 *
 * @Author: 竺志伟
 * @Date: 2019-07-21 13:30
 */
public class JdbcRealmTest
{
    DruidDataSource druidDataSource = new DruidDataSource();

    {
        druidDataSource.setUrl("");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
    }

    @Test
    public void test()
    {
        // 配置文件 realm  领域
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        jdbcRealm.setPermissionsLookupEnabled(true); //开启权限查询数据

        // 构建 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        // 主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        // 用户名密码登录
        UsernamePasswordToken token = new UsernamePasswordToken("nature", "123456");
        subject.login(token);

        System.out.println(subject.isAuthenticated());
        Assert.assertTrue(subject.isAuthenticated());

        // 授权 角色检查
        subject.checkRole("admin");
        //roles 权限检查
        subject.checkPermission("user:add");

        subject.logout();

    }
}
