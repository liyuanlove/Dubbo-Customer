package com.coder.dubbo.customer.util;

import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.springbootdomecollection.model.SysRole;
import com.coder.springbootdomecollection.model.SysUser;
import com.coder.springbootdomecollection.service.SysMenuService;
import com.coder.util.CollectionUtils;
import org.apache.shiro.SecurityUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 当前登录人信息
 */
public final class Current {

    public static SysUser user;

    public static String currentSessionID(){
        return SecurityUtils.getSubject().getSession().getId().toString();
    }

    public static SysUser currentSysUser(){
        try {
            return (SysUser) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public static Set<SysMenu> Menu(){
        Set<SysMenu> menus = new HashSet<>();
        SysUser sysUser = currentSysUser();
        List<SysRole> roles = sysUser.getRoleList();
        if(!CollectionUtils.isNullOrEmptyStrict(roles)){
            for(SysRole role : roles){
                List<SysMenu> roleMenus = role.getSysMenuList();
                if(!CollectionUtils.isNullOrEmptyStrict(roleMenus)){
                    for(SysMenu sysMenu : roleMenus){
                        menus.add(sysMenu);
                    }
                }
            }
        }
        return menus;
    }
}
