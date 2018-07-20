package com.coder.dubbo.customer.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@Scope("prototype")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping
    public String Index(Model model){
        return "index";
    }

}
