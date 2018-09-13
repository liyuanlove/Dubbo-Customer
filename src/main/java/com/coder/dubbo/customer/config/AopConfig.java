package com.coder.dubbo.customer.config;

import com.coder.dubbo.customer.util.Current;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

@Aspect
@Configuration
public class AopConfig {

    @Pointcut("execution(* com.coder..view.*.*(org.springframework.ui.Model))")
    public void executeViewController(){ }

    /**
     * 在切入点之前
     * @param jp
     */
    @Before("executeViewController()")
    public void beforeViewController(JoinPoint jp){
        System.out.println("前置拦截器");
        if(jp != null){
            for(Object obj:jp.getArgs()){
                if(obj instanceof Model){
                    ((Model) obj).addAttribute("menus",Current.());
                }
            }
        }
    }
}
