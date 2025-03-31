package com.social.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class testController {

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/test")
    public String test()
    {
        try{
            messageProducer.sendMessage("test");
        }catch (Exception e){
            log.error("RabbitMQ发送消息失败", e);
        }

        return "test";
    }
}
