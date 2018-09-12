package com.coder.dubbo.customer.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.coder.util.MD5Encrypt.MD5Encode;

@Controller
@RequestMapping("/")
@Scope("prototype")
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String name,String password){
        Subject subject = SecurityUtils.getSubject();//获取当前用户对象
        if (!subject.isAuthenticated()) {
            // 把用户名和密码封装为UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(name, MD5Encode(MD5Encode(password)));
            token.setRememberMe(true);
            try {
                // 执行登陆
                subject.login(token);
                return "/main";
            } catch ( UnknownAccountException uae ) {
                //username wasn't in the system, show them an error message?
                System.out.println("账号不存在");
            } catch ( IncorrectCredentialsException ice ) {
                //password didn't match, try again?
                System.out.println("密码错误");
            } catch ( LockedAccountException lae ) {
                //account for that username is locked - can't login.  Show them a message?
                System.out.println("账号被锁");
            } catch (AuthenticationException ae) {
                System.out.println("登录失败--->" + ae.getMessage());
            }
        }
        return "login";
    }

    @GetMapping
    public String Index(){
        return "index";
    }

    @GetMapping("/getsession")
    @ResponseBody
    public String getSessionId(HttpServletRequest req){
        Object o = req.getSession().getAttribute("springboot");
        if(o == null){
            o = "端口：" + req.getLocalPort() + "生成SessionId:" + req.getSession().getId();
            req.getSession().setAttribute("springboot", o);
        }
        return o + "<br/>当前端口=" + req.getLocalPort() +  " sessionId=" + req.getSession().getId() +"<br/>";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/lovedongqing")
    public String loveDongQing(){
        return "dongqing";
    }
}
