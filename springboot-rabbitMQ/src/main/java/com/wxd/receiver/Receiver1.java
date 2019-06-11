package com.wxd.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_topic_message")
public class Receiver1 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
   /* @RabbitListener(queues = "q_topic_message")
    public void processMessage1(@Payload String body, @Headers Map<String,Object> headers) {
        System.out.println("body："+body);
        System.out.println("Headers："+headers);
    }*/
}
