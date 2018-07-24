package com.coder.dubbo.customer.controller;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@Scope("prototype")
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping
    public String Index(){
        return "index";
    }

    @GetMapping("/GetSession")
    @ResponseBody
    public String getSessionId(HttpServletRequest req){
        Object o = req.getSession().getAttribute("springboot");
        if(o == null){
            o = "端口：" + req.getLocalPort() + "生成SessionId:" + req.getSession().getId();
            req.getSession().setAttribute("springboot", o);
        }
        return o + "<br/>当前端口=" + req.getLocalPort() +  " sessionId=" + req.getSession().getId() +"<br/>";
    }

    @GetMapping("/Main")
    public String Main(){
        return "main";
    }
}
