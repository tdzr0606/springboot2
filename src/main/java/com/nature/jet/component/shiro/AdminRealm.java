package com.nature.jet.component.shiro;

import com.nature.jet.pojo.web.Admin;
import com.nature.jet.pojo.web.AdminRight;
import com.nature.jet.pojo.web.RolesRight;
import com.nature.jet.service.web.AdminRightService;
import com.nature.jet.service.web.AdminService;
import com.nature.jet.service.web.RolesRightService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.checkerframework.checker.units.qual.A;
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
@Slf4j
public class AdminRealm extends AuthorizingRealm
{
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRightService adminRightService;
    @Autowired
    RolesRightService rolesRightService;

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

        rightList.stream().forEach(adminRight ->
        {
            roles.add(adminRight.getRoleEnTitle());
            List<RolesRight> rolesRightList = rolesRightService.listByRoleId(adminRight.getRoleId());
            rolesRightList.stream().forEach(rolesRight ->
            {
                permissions.add(rolesRight.getModuleEnTitle().concat(":").concat(rolesRight.getModuleRoleEnTitle()));
            });
        });

        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        simpleAuthenticationInfo.setRoles(roles);
        simpleAuthenticationInfo.setStringPermissions(permissions);
        log.info("权限获取");
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
        log.info("角色验证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Admin admin = adminService.login(token.getUsername(), new String(token.getPassword()));
        if(null != admin)
        {
            return new SimpleAuthenticationInfo(admin, token.getPassword(), getName());
        }
        return null;
    }


    /**
     * 清理shiro缓存
     * Clear cached.
     *
     * @param principals the principals
     * @author:竺志伟
     * @date :2019-09-15 16:31:22
     */
    public void clearCached(PrincipalCollection principals)
    {
        log.info("缓存清理");
        super.clearCache(principals);
    }
}
