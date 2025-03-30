package com.social;

import com.social.dao.GetUserMapper;
import com.social.dao.entity.User;
import com.social.redis.RedissonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@MapperScan("com.social.dao")
public class TestApplication {

    @Autowired
    private GetUserMapper getUserMapper;

    @Autowired
    private RedissonService redissonService;

    @Test
    public void test() {
        System.out.println("hello world");
        List<User> users= getUserMapper.getAllUsers();
    }

    //测试redisson
    @Test
    public void testRedisson() {
        User user = new User(1, "test", 18);
        redissonService.setValue("user", user);
        User user1 = redissonService.getValue("user");
        System.out.println(user1);
    }
}
