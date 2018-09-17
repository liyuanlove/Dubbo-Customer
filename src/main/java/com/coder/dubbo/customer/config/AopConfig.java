package com.coder.dubbo.customer.config;

import com.coder.dubbo.customer.util.Current;
import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.util.CollectionUtils;
import com.coder.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Configuration
public class AopConfig {

    @Pointcut("execution(* com.coder..controller.view.*.*(..,org.springframework.ui.Model,..))")
    public void executeViewController(){ }

    @AfterReturning(pointcut="execution(* com.coder..controller.view.*.*(..,org.springframework.ui.Model,..))")
    public void afterReturningViewController(JoinPoint jp){
        for(Object obj:jp.getArgs()){
            if(obj instanceof Model){
                ((Model) obj).addAttribute("title",StringUtils.EMPTY);
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String uri = StringUtils.null2Empty(request.getRequestURI());
                ((Model) obj).addAttribute("menus",Current.menus());
                List<SysMenu> menus = Current.menus();
                block:{
                    if(!CollectionUtils.isNullOrEmptyStrict(menus)){
                        for(SysMenu menu : menus){
                            List<SysMenu> childMenus = menu.getChildrenMenus();
                            if(!CollectionUtils.isNullOrEmptyStrict(childMenus)){
                                for(SysMenu childMenu : childMenus){
                                    if(uri.equals(childMenu.getUrl())){
                                        ((Model) obj).addAttribute("title",childMenu.getName() + " - ");
                                        ((Model) obj).addAttribute("parentmenu",menu.getUrl());
                                        ((Model) obj).addAttribute("childmenu",uri);
                                        break block;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
