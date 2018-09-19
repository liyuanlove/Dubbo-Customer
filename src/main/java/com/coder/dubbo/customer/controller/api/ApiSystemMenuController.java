package com.coder.dubbo.customer.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.coder.dubbo.customer.enumeration.State;
import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.springbootdomecollection.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Scope("prototype")
@RestController
@RequestMapping("/api/menu")
public class ApiSystemMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping()
    public List<SysMenu> Save(SysMenu sysMenu){
        int i = sysMenuService.save(sysMenu);
        JSONObject json = new JSONObject();
        if(i > 0){
            json.put("code",State.SUCCESS);
            json.put("msg",State.SUCCESS.getValue());
        }
    }

}
