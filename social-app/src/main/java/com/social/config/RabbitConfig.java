package com.social.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 定义队列
    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true);  // true表示持久化队列
    }

    // 定义交换机
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("myExchange");
    }

    // 绑定队列到交换机
    @Bean
    public Binding binding(Queue myQueue, TopicExchange exchange) {
        return BindingBuilder.bind(myQueue).to(exchange).with("my.routing.key");
    }
}
