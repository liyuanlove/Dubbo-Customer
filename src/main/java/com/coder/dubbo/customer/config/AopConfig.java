package com.coder.dubbo.customer.config;

import com.coder.dubbo.customer.util.Current;
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

@Aspect
@Configuration
public class AopConfig {

    @Pointcut("execution(* com.coder..controller.view.*.*(..,org.springframework.ui.Model,..))")
    public void executeViewController(){ }

    @AfterReturning(pointcut="execution(* com.coder..controller.view.*.*(..,org.springframework.ui.Model,..))")
    public void afterReturningViewController(JoinPoint jp){
        for(Object obj:jp.getArgs()){
            if(obj instanceof Model){
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                ((Model) obj).addAttribute("menus",Current.menus());
                ((Model) obj).addAttribute("thismenu",request.getRequestURI());
            }
        }
    }
}
