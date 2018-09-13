package com.coder.dubbo.customer.controller;

import com.alibaba.fastjson.JSONObject;
import com.coder.dubbo.customer.enumeration.State;
import com.coder.springbootdomecollection.model.Mail;
import com.coder.springbootdomecollection.service.MailService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.io.Serializable;

@RestController
@RequestMapping("/Mail")
@Scope("prototype")
public class MailController {

    @Autowired
    private MailService mailService;

    private JSONObject json = new JSONObject();

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsTemplate jmsTemplate;

    private static final String SENDMAIL_QUEUE = "sendmail.queue";

    @PostMapping
    public JSONObject sendMail(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("content") String content){

        boolean flag = mailService.sendMail(to, subject, content);
        if(flag){
            json.put("state",State.SUCCESS.getValue());
            json.put("msg","发送成功");
            return json;
        }else{
            json.put("state",State.FAIL.getValue());
            json.put("state","发送失败");
        }
        return json;
    }

    @PostMapping("/Queue")
    public JSONObject sendMailQueue(Mail mail){
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            factory.setTrustAllPackages(true);
            Destination destination = new ActiveMQQueue(SENDMAIL_QUEUE);
            jmsTemplate.send(destination, session -> session.createObjectMessage((Serializable) mail));
            json.put("state",State.SUCCESS.getValue());
            json.put("msg","邮件已发送，如果长时间未收到，请再次发送");
        }catch (MessagingException e){
            json.put("state",State.FAIL.getValue());
            json.put("msg",e.getMessage());
        }
        return json;
    }

}
