package com.coder.dubbo.customer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coder.springbootdomecollection.model.User;
import com.coder.springbootdomecollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@RestController
/**
 * 将此类的方法的返回值以json的形式返回
 */
@Scope("prototype")
@RequestMapping(value = "/user",produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    private JSONObject json = new JSONObject();

    @RequestMapping(value = "/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Object list(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userService.list(pageNum,pageSize);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(User user){
        int i = userService.add(user);
        if(i > 0){
            json.put("state",1);
            json.put("msg","保存成功");
            return json.toJSONString();
        }
        json.put("state",2);
        json.put("msg","保存失败");
        return json.toJSONString();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(User user){
        int i = userService.update(user);
        if(i > 0){
            json.put("state",1);
            json.put("msg","修改成功");
            return  json.toJSONString();
        }
        json.put("state",2);
        json.put("msg","修改失败");
        return  json.toJSONString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User selectOne(@PathVariable int id){
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/j/{id}",method = RequestMethod.GET)
    public String selectUser(@PathVariable int id){
        return JSON.toJSONString(userService.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable int id){
        int i = userService.delete(id);
        if(i > 0){
            json.put("state",1);
            json.put("msg","删除成功");
            return json.toJSONString();
        }
        json.put("state",2);
        json.put("msg","删除失败");
        return json.toJSONString();
    }

    @GetMapping("/findByName/{name}")
    public User findByName(@PathVariable String name) {
        User user = userService.findUserInMongoDB(name);
        return user;
    }
}
