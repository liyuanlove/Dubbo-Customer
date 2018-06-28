package com.coder.dubbo.customer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.coder.dubbo.customer.enumeration.State;

import com.coder.springbootdomecollection.model.User;
import com.coder.springbootdomecollection.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@RestController
/**
 * 将此类的方法的返回值以json的形式返回
 */
@Scope("prototype")
@RequestMapping(value = "/user",produces = "application/json;charset=UTF-8")
@Api(value = "/user",tags = "操作用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    private JSONObject json = new JSONObject();

    @RequestMapping(value = "/{pageNum}/{pageSize}",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum" ,value = "开始页码",required = true,dataType="int",paramType = "path"),
            @ApiImplicitParam(name = "pageSize",value = "每页条数",required = true,dataType = "int",paramType = "path")
    })
    public Object list(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userService.list(pageNum,pageSize);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "添加用户",notes = "添加用户")
    /* 想在swagger中 输入user的json对象 就把下面的注释打开，想在每个输入框中输入User的属性值，就不打开*/
    /* 这两个注解 @ApiImplicitParam @RequestBody */
    /*@ApiImplicitParam(name = "user",value = "用户",required = true,dataType = "User",paramType = "body")*/
    public String add(/*@RequestBody*/ User user){
        int i = userService.add(user);
        if(i > 0){
            json.put("state",State.SUCCESS.getValue());
            json.put("msg","保存成功");
            return json.toJSONString();
        }
        json.put("state",State.FAIL.getValue());
        json.put("msg","保存失败");
        return json.toJSONString();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户",notes = "更新用户")
    /* 想在swagger中 输入user的json对象 就把下面的注释打开，想在每个输入框中输入User的属性值，就不打开*/
    /* @ApiImplicitParam @RequestBody */
    /*@ApiImplicitParam(name = "user",value = "用户",required = true,dataType = "User",paramType = "head")*/
    public String update(/*@RequestBody */User user){
        int i = userService.update(user);
        if(i > 0){
            json.put("state",State.SUCCESS.getValue());
            json.put("msg","修改成功");
            return  json.toJSONString();
        }
        json.put("state",State.FAIL.getValue());
        json.put("msg","修改失败");
        return  json.toJSONString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询一个用户",notes = "查询一个用户")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "int",paramType = "path")
    public User selectOne(@PathVariable int id){
        return userService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/j/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "查询一个用户，使用fastjson返回",notes = "查询一个用户，使用fastjson返回")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "int",paramType = "path")
    public String selectUser(@PathVariable int id){
        return JSON.toJSONString(userService.selectByPrimaryKey(id));
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除一个用户",notes = "删除一个用户")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "int",paramType = "path")
    public String delete(@PathVariable int id){
        int i = userService.delete(id);
        if(i > 0){
            json.put("state",State.SUCCESS.getValue());
            json.put("msg","删除成功");
            return json.toJSONString();
        }
        json.put("state",State.FAIL.getValue());
        json.put("msg","删除失败");
        return json.toJSONString();
    }

    @GetMapping("/findByName/{name}")
    @ApiOperation(value = "从MongoDB中查询一个用户",notes = "从MongoDB中查询一个用户")
    @ApiImplicitParam(name = "name",value = "用户name",required = true,dataType = "String",paramType = "path")
    public User findByName(@PathVariable String name) {
        User user = userService.findUserInMongoDB(name);
        return user;
    }
}
