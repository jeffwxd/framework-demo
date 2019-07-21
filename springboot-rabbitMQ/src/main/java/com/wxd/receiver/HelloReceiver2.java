package com.wxd.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_hell")
public class HelloReceiver2 {

    @RabbitHandler
    public void process01(String hello) {
        System.out.println("Receiver2  : " + hello);
    }

}
