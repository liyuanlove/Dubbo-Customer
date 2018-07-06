package com.coder.dubbo.customer.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

@RestController
@RequestMapping(value = "/Queue")
public class QueueController {

    private static final String TEST_QUEUE = "test.queue";
    private static final String OUT_QUEUE = "out.queue";

    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    @RequestMapping(value = "/{message}",method = RequestMethod.GET)
    public void sendMessage(@PathVariable String message){
        Destination destination = new ActiveMQQueue(TEST_QUEUE);
        for(int i=0;i<10;i++){
            jmsTemplate.convertAndSend(destination,message + ":" + (i+1));
        }
    }

    @JmsListener(destination=OUT_QUEUE,containerFactory = "queueListenerFactory")
    public void consumerMessage(String text){
        System.out.println(text);
    }

}
