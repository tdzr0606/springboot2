package com.nature.jet.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * springboot2
 * AuthenticationTest
 *
 * @Author: 竺志伟
 * @Date: 2019-07-21 13:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTest
{
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();


    @Before
    public void adduser()
    {
        simpleAccountRealm.addAccount("nature","123456","admin","user");
    }

    @Test
    public void test()
    {
        // 构建 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        // 用户名密码登录
        UsernamePasswordToken token = new UsernamePasswordToken("nature","123456");
        subject.login(token);

        System.out.println(subject.isAuthenticated());
        Assert.assertTrue(subject.isAuthenticated());

        // 授权检查
        subject.checkRole("admin");

        subject.logout();

    }
}
