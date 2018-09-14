package com.coder.dubbo.customer;

import com.coder.springbootdomecollection.model.SysUser;
import com.coder.springbootdomecollection.service.SysUserService;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testSelectByProperty(){
        SysUser user = new SysUser();
        user.setName("735626035@qq.com");
        SysUser thisuser = sysUserService.selectByProperty(user);
        System.out.println(thisuser);
    }
}
