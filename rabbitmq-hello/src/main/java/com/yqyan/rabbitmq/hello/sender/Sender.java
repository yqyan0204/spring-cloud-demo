package com.yqyan.rabbitmq.hello.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.yqyan.rabbitmq.hello.common.Constants.HELLO_QUEUE;

/**
 * @Author BG388812
 * @Date 2019/6/25 16:07
 **/

@Component
public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String message){
        LOGGER.info("Send message: {}", message);
        this.amqpTemplate.convertAndSend(HELLO_QUEUE, message);
    }

}
