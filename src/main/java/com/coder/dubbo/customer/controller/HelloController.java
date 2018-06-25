package com.coder.dubbo.customer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/getsession")
    public String getSessionId(HttpServletRequest req){

        Object o = req.getSession().getAttribute("springboot");
        if(o == null){
            o = "端口：" + req.getLocalPort() + "生成SessionId:" + req.getSession().getId();
            req.getSession().setAttribute("springboot", o);
        }
        return o + "<br/>当前端口=" + req.getLocalPort() +  " sessionId=" + req.getSession().getId() +"<br/>";
    }

}
