package com.nature.jet.component.shiro;

import com.nature.jet.pojo.web.Admin;
import com.nature.jet.pojo.web.AdminRight;
import com.nature.jet.service.web.AdminRightService;
import com.nature.jet.service.web.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * springboot2
 * AdminRealm
 *
 * @Author: 竺志伟
 * @Date: 2019-07-21 15:58
 */
@Component
public class AdminRealm extends AuthorizingRealm
{
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRightService adminRightService;

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
        List<AdminRight> rightList = adminRightService.listByAdminId(admin.getId());
        // 角色
        final Set<String> roles = new HashSet<>();
        // 权限
        final Set<String> permissions = new HashSet<>();

        rightList.stream().forEach(adminRight -> {
            roles.add(adminRight.getRoleEnTitle());
            String[] rightText =adminRight.getRightText().split(",") ;
            for(String right : rightText)
            {
                permissions.add(right);
            }
        });

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
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Admin admin = adminService.login(token.getUsername(), new String(token.getPassword()));
        if(null != admin)
        {
            return new SimpleAuthenticationInfo(admin, token.getPassword(), getName());
        }
        return null;
    }
}
