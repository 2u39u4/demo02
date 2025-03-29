package com.social;

import com.social.dao.GetUserMapper;
import com.social.dao.entity.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@MapperScan("com.social.dao")
public class TestApplication {

    @Autowired
    private GetUserMapper getUserMapper;

    @Test
    public void test() {
        System.out.println("hello world");
//        List<Animal> animals = animalMapper.getAllAnimals();
//        animals.forEach(System.out::println);
        List<Animal> animals = getUserMapper.getAllAnimals();
        animals.forEach(System.out::println);
//        System.out.println(getUserMapper.getAllAnimals());
    }
}
