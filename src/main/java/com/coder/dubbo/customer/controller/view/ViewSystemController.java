package com.coder.dubbo.customer.controller.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 吴俊龙
 * @version 1.0
 * @createdate 2018-09-15 22:21
 */

@Controller
@Scope("prototype")
@RequestMapping("/system")
public class ViewSystemController {

    @GetMapping("/user")
    public String user(Model model) {
        return "system/user";
    }

    @GetMapping("/role")
    public String role(Model model) {
        return "system/role";
    }

    @GetMapping("/permission")
    public String permission(Model model) {
        return "system/permission";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        return "system/menu";
    }

    @GetMapping("/show")
    public String show(Model model) {
        return "doublecolorball/index";
    }

}
