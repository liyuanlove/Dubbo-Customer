package com.coder.dubbo.customer.config;

import com.coder.dubbo.customer.util.Current;
import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.util.CollectionUtils;
import com.coder.util.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
                ((Model) obj).addAttribute("menus",Current.menus());
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String uri = StringUtils.null2Empty(request.getRequestURI());
                List<SysMenu> menus = Current.menus();
                block:{
                    if(!CollectionUtils.isNullOrEmptyStrict(menus)){
                        for(SysMenu menu : menus){
                            menu.setShow(Boolean.FALSE);
                            if(uri.equals(menu.getUrl())){
                                menu.setShow(Boolean.TRUE);
                                ((Model) obj).addAttribute("title",menu.getName() + " - ");
                                ((Model) obj).addAttribute("parentMenu",menu);
                                break block;
                            }
                            List<SysMenu> childMenus = menu.getChildrenMenus();
                            if(!CollectionUtils.isNullOrEmptyStrict(childMenus)){
                                for(SysMenu childMenu : childMenus){
                                    childMenu.setShow(Boolean.FALSE);
                                    if(uri.equals(childMenu.getUrl())){
                                        menu.setShow(Boolean.TRUE);
                                        childMenu.setShow(Boolean.TRUE);
                                        ((Model) obj).addAttribute("title",childMenu.getName() + " - ");
                                        ((Model) obj).addAttribute("parentMenu",menu);
                                        ((Model) obj).addAttribute("childMenu",childMenu);
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
