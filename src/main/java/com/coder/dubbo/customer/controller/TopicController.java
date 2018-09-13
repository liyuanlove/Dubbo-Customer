package com.coder.dubbo.customer.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;

@RestController
@RequestMapping("/Topic")
@Scope("prototype")
public class TopicController {

    private static final String TEST_TOPIC = "test.topic";

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    @RequestMapping(value = "/{message}",method = RequestMethod.GET)
    public void sendMessage(@PathVariable String message){
        Destination destination = new ActiveMQTopic(TEST_TOPIC);
        for(int i = 0; i<10 ; i++){
            jmsTemplate.convertAndSend(destination,message+(i+1));
        }
    }

}
