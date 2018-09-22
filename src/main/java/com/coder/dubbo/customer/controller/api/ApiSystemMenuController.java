package com.coder.dubbo.customer.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.coder.dubbo.customer.enumeration.State;
import com.coder.dubbo.customer.util.JsonUtils;
import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.springbootdomecollection.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("prototype")
@RestController
@RequestMapping("/api/menu")
public class ApiSystemMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping()
    public String save(SysMenu sysMenu){
        int i = sysMenuService.save(sysMenu);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        if(i > 0){
            SysMenu menu = sysMenuService.selectByPrimaryKey(sysMenu.getPid());
            json.put("menu",menu);
        }
        return json.toJSONString();
    }

    @PostMapping("/parentmenu")
    public String saveMenu(SysMenu sysMenu){
        int i = sysMenuService.save(sysMenu);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        if(i > 0){
            List<SysMenu> menus = sysMenuService.selectAll();
            json.put("menus",menus);
        }
        return json.toJSONString();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        int i = sysMenuService.deleteByPrimaryKey(id);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        return json.toJSONString();
    }
}
