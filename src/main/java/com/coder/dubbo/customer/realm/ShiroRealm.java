package com.coder.dubbo.customer.realm;

import com.coder.springbootdomecollection.model.SysPermission;
import com.coder.springbootdomecollection.model.SysRole;
import com.coder.springbootdomecollection.model.SysUser;
import com.coder.springbootdomecollection.model.User;
import com.coder.springbootdomecollection.service.SysUserService;
import com.coder.springbootdomecollection.service.UserService;
import com.coder.util.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * 在认证、授权内部实现机制中都有提到，最终处理都将交给Realm进行处理。
 * 因为在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的。
 * 通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO.
 * Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法。
 *
 * 该方法主要执行以下操作:
 * 检查提交的进行认证的令牌信息
 * 根据令牌信息从数据源(通常为数据库)中获取用户信息
 * 对用户信息进行匹配验证。
 * 验证通过将返回一个封装了用户信息的AuthenticationInfo实例。
 * 验证失败则抛出AuthenticationException异常信息。
 * 而在我们的应用程序中要做的就是自定义一个Realm类，继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo()，重写获取用户信息的方法。
 */

public class ShiroRealm extends AuthorizingRealm {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 验证用户身份，
     * 也就是说验证用户输入的账号和密码是否正确
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        System.out.println("ShiroRealm.doGetAuthenticationInfo()");
        System.out.println(authenticationToken.getPrincipal());
        System.out.println(authenticationToken.getCredentials());
        //获取用户的输入的账号.
        SysUser sysUser = (SysUser) authenticationToken.getPrincipal();
        if (sysUser == null) {
            return null;
        }
        sysUser = sysUserService.selectByPrimaryKey(sysUser.getId());
        System.out.println("----->>userInfo="+sysUser.getName());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //用户名
                sysUser,
                //密码
                sysUser.getPassword(),
                //realm name
                getName()
        );
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限配置-->ShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        if(!CollectionUtils.isNullOrEmpty(sysUser.getRoleList())){
            for (SysRole role : sysUser.getRoleList()) {
                authorizationInfo.addRole(role.getRname());
                if(!CollectionUtils.isNullOrEmpty(role.getSysPermissionList())){
                    for (SysPermission p : role.getSysPermissionList()) {
                        authorizationInfo.addStringPermission(p.getName());
                    }
                }
            }
        }
        return authorizationInfo;
    }

}