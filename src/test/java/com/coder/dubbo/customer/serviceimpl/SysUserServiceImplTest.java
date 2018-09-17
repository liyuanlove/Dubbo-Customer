package com.coder.dubbo.customer.serviceimpl;

import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.springbootdomecollection.model.SysPermission;
import com.coder.springbootdomecollection.model.SysRole;
import com.coder.springbootdomecollection.model.SysUser;
import com.coder.springbootdomecollection.service.SysUserService;
import com.coder.util.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceImplTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void selectByPrimaryKey(){
        SysUser temp = new SysUser();
        temp.setName("735626035@qq.com");
        SysUser sysUser = sysUserService.selectByProperty(temp);
        List<SysRole> roles = sysUser.getRoleList();
        List<SysMenu> menus = sysUser.getMenuList();
        if(!CollectionUtils.isNullOrEmptyStrict(roles)){
            for(SysRole role : roles){
                System.out.println(role.getRname() + ":");
                List<SysPermission> sysPermissions = role.getSysPermissionList();
                if(!CollectionUtils.isNullOrEmptyStrict(sysPermissions)){
                    for(SysPermission sysPermission : sysPermissions){
                        System.out.println("  " + sysPermission.getName());
                    }
                }
            }
        }
        if(!CollectionUtils.isNullOrEmptyStrict(menus)){
            for(SysMenu menu : menus){
                System.out.println(menu.getName() + ":");
                for (SysMenu cmenu : menu.getChildrenMenus()){
                    System.out.println("  " + cmenu.getName());
                }
            }
        }
    }

    @Test
    public void testSelectByProperty(){
        SysUser user = new SysUser();
        user.setName("735626035@qq.com");
        SysUser temp = sysUserService.selectByProperty(user);
        System.out.println(temp);
    }
}