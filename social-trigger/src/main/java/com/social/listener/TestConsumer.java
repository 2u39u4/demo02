package com.social.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestConsumer {
    @RabbitListener(queues = "myQueue")
    public void receiveMessage(String message) {
//        System.out.println("Received message: " + message);
        log.info("Received message: " + message);
    }
}
