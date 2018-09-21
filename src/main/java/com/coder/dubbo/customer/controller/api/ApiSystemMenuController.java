package com.coder.dubbo.customer.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.coder.dubbo.customer.enumeration.State;
import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.springbootdomecollection.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

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
        if(i > 0){
            json.put("code",State.SUCCESS.getValue());
            json.put("state",State.SUCCESS);
            json.put("msg",State.SUCCESS);
            SysMenu pmenu = sysMenuService.selectByPrimaryKey(sysMenu.getPid());
            json.put("menu",pmenu);
        }else{
            json.put("code",State.FAIL.getValue());
            json.put("state",State.FAIL);
            json.put("msg",State.FAIL);
        }
        return json.toJSONString();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        int i = sysMenuService.deleteByPrimaryKey(id);
        JSONObject json = new JSONObject();
        if(i > 0){
            json.put("code",State.SUCCESS.getValue());
            json.put("state",State.SUCCESS);
            json.put("msg",State.SUCCESS);
        }else{
            json.put("code",State.FAIL.getValue());
            json.put("state",State.FAIL);
            json.put("msg",State.FAIL);
        }
        return json.toJSONString();
    }

}
