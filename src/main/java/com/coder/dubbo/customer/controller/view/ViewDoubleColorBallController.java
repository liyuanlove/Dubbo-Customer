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
@RequestMapping("/doublecolorball")
public class ViewDoubleColorBallController {

    @GetMapping
    public String index(Model model) {
        return "doublecolorball/index";
    }
    @GetMapping("/show")
    public String show(Model model) {
        return "doublecolorball/index";
    }

}
