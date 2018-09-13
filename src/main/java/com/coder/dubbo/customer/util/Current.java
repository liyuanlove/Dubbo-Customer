package com.coder.dubbo.customer.util;

import com.coder.springbootdomecollection.model.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * 当前登录人信息
 */
public final class Current {

    public static String CurrentSessionID(){
        return SecurityUtils.getSubject().getSession().getId().toString();
    }

    public static SysUser CurrentSysUser(){
        try {
            return (SysUser) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
