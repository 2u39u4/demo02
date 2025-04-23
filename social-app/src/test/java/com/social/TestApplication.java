package com.social;

import com.social.config.MessageProducer;
import com.social.dao.mysql.GetUserMapper;
import com.social.redis.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
//@MapperScan("com.social.dao")
public class TestApplication {

    @Autowired
    private GetUserMapper getUserMapper;

    @Test
    public void testMapper() {
//        System.out.println("hello world");
//        List<User> users= getUserMapper.getAllUsers();
//        users.forEach(System.out::println);
    }

    //测试redisson
    @Autowired
    private RedissonService redissonService;
    @Test
    public void testRedisson() {
//        User user = new User(1, "test", 18);
//        redissonService.setValue("user", user);
//        User user1 = redissonService.getValue("user");
//        System.out.println(user1);
    }

    //测试logback
    @Test
    public void testLogback() {
        log.info("info hello");
        log.debug("debug yes");
        log.error("error xxxxxxx");
    }

    //测试rabbitmq
    @Autowired
    private MessageProducer messageProducer;
    @Test
    public void testRabbitmq() {
        try{
            messageProducer.sendMessage("Hello, RabbitMQ!");
        }catch (Exception e){
            log.error("RabbitMQ发送消息失败", e);
        }
    }

}
