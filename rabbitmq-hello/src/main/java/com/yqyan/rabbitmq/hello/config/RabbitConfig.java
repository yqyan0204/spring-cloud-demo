package com.yqyan.rabbitmq.hello.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.yqyan.rabbitmq.hello.common.Constants.HELLO_QUEUE;

/**
 * @Author BG388812
 * @Date 2019/6/25 16:19
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue(HELLO_QUEUE);
    }

}
