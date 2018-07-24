package com.coder.dubbo.customer.realm;

import com.coder.springbootdomecollection.model.User;
import com.coder.springbootdomecollection.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
    private UserService userService;

    /**
     * 验证用户身份，
     * 也就是说验证用户输入的账号和密码是否正确
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //System.out.println("权限配置-->ShiroRealm.doGetAuthorizationInfo()");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        User user = (User) principals.getPrimaryPrincipal();
//        for (SysRole role : user.getRoleList()) {
//            authorizationInfo.addRole(role.getRole());
//            for (SysPermission p : role.getPermissions()) {
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
//        return authorizationInfo;
//    }
//
//    /**
//     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
//     * */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
//            throws AuthenticationException {
//        //System.out.println("ShiroRealm.doGetAuthenticationInfo()");
//        //获取用户的输入的账号.
//        String username = (String) token.getPrincipal();
//        //System.out.println(token.getCredentials());
//        //通过username从数据库中查找 User对象，如果找到，没找到.
//        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        UserInfo userInfo = userInfoService.findByUsername(username);
//        System.out.println("----->>userInfo="+userInfo);
//        if (userInfo == null) {
//            return null;
//        }
//        //账户冻结
//        if (userInfo.getState() == 1) {
//            throw new LockedAccountException();
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                //用户名
//                userInfo,
//                //密码
//                userInfo.getPassword(),
//                //salt=username+salt
//                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
//                //realm name
//                getName()
//        );
//        return authenticationInfo;
//    }

}