package com.nature.jet.test;

import com.nature.jet.pojo.web.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义 realm
 * springboot2
 * CustomRealmTest
 *
 * @Author: 竺志伟
 * @Date: 2019-07-21 13:42
 */
public class CustomRealmTest
{
    @Test
    public void test()
    {
        // 配置文件 realm  领域
        CustomRealm customRealm = new CustomRealm();

        // 构建 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        // 主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        // 用户名密码登录
        CustomLoginToken token = new CustomLoginToken("nature", "123456", 1);
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


/**
 * 自定义登录处理
 * The type Custom realm.
 *
 * @author:竺志伟
 * @date :2019-07-21 14:17:07
 */
class CustomRealm extends AuthorizingRealm
{
    /**
     * 授权
     * Do get authorization info authorization info.
     *
     * @param principalCollection the principal collection
     * @return the authorization info
     * @author:竺志伟
     * @date :2019-07-21 13:48:56
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        // 角色
        Set<String> roles = new HashSet<>();
        roles.add("admin");

        // 权限
        Set<String> permissions = new HashSet<>();
        permissions.add("user:add");

        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        simpleAuthenticationInfo.setRoles(roles);
        simpleAuthenticationInfo.setStringPermissions(permissions);
        return simpleAuthenticationInfo;
    }


    /**
     * 做登录 用户验证
     * Do get authentication info authentication info.
     *
     * @param authenticationToken the authentication token
     * @return the authentication info
     * @throws AuthenticationException the authentication exception
     * @author:竺志伟
     * @date :2019-07-21 13:47:02
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException
    {
        CustomLoginToken token = (CustomLoginToken) authenticationToken;
        String user = token.getUsername();
        String pass = new String(token.getPassword());
        if(user.equalsIgnoreCase("nature") && pass.equalsIgnoreCase("e10adc3949ba59abbe56e057f20f883e"))
        {
            Admin admin = new Admin();
            admin.setId(1);
            admin.setIsPublic(true);
            admin.setLoginName(user);
            admin.setUserName(user);
            return new SimpleAuthenticationInfo(admin, pass, getName());
        }
        return null;
    }
}


/**
 * 自定义登录token
 * The type Custom login toke.
 *
 * @author:竺志伟
 * @date :2019-07-21 14:17:17
 */
class CustomLoginToken extends UsernamePasswordToken
{
    private int unitId;

    public CustomLoginToken(String username, String password, int unitId)
    {
        super(username, password);
        this.unitId = unitId;
    }

    public int getUnitId()
    {
        return unitId;
    }

    public void setUnitId(int unitId)
    {
        this.unitId = unitId;
    }
}
