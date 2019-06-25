package com.yqyan.rabbitmq.hello;

import com.yqyan.rabbitmq.hello.sender.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqHelloApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private Sender sender;

    @Test
    public void hello() throws Exception{
        String message = "Hail Hydra!";
        sender.send(message);
    }

}
