package com.coder.dubbo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import com.coder.springbootdomecollection.service.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/Zookeeper")
@Scope("prototype")
public class ZookeeperController {

    @Autowired
    private ZookeeperService zookeeperService;

    @GetMapping("/{name}")
    public String getZookeeper(@PathVariable("name") String name){
        return zookeeperService.get(name);
    }

    @PostMapping()
    public String addZookeeper(@RequestParam("nodeName") String nodeName,@RequestParam("value") String value){
        return zookeeperService.add(nodeName,value);
    }

    @PutMapping()
    public String updateZookeeper(@RequestParam("nodeName") String nodeName,@RequestParam("value") String value){
        return zookeeperService.update(nodeName,value);
    }

}
