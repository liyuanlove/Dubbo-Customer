package com.coder.dubbo.customer.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;

@RestController
@RequestMapping("/DoubleColorBall")
public class DoubleColorBallController {

    private static final String RETURN_DOUBLE_COLOR_BALL = "returndoublecolorball.queue";
    private static final String INSERT_DOUBLE_COLOR_BALL = "insertdoublecolorball.queue";
    private static final String UPDATE_DOUBLE_COLOR_BALL = "updatedoublecolorball.queue";

    @JmsListener(destination=RETURN_DOUBLE_COLOR_BALL,containerFactory = "queueListenerFactory")
    public void consumerMessage(String text){
        System.out.println(text);
    }

    @PostMapping()
    public void sendMessage(@PathVariable String message){
        Destination destination = new ActiveMQQueue(TEST_QUEUE);
        for(int i=0;i<10;i++){
            jmsTemplate.convertAndSend(destination,message + ":" + (i+1));
        }
    }
}
