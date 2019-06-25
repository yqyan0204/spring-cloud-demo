package com.yqyan.rabbitmq.hello.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.yqyan.rabbitmq.hello.common.Constants.HELLO_QUEUE;

/**
 * @Author BG388812
 * @Date 2019/6/25 16:16
 **/
@Component
@RabbitListener(queues = HELLO_QUEUE)
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);


    @RabbitHandler
    public void process(String message){
        LOGGER.info("Receive message: {}", message);
    }

}
