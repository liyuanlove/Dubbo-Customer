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

    public static String siessionId(){
        return SecurityUtils.getSubject().getSession().getId().toString();
    }

    public static SysUser user(){
        try {
            return (SysUser) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public static List<SysMenu> menus(){
        List<SysMenu> menus = user().getMenuList();
        return menus;
    }
}
