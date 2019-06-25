package com.yqyan.config.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author BG388812
 * @Date 2019/6/24 15:46
 **/
@RefreshScope
@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment env;

    @RequestMapping("/from")
    public String from(){
        return this.from;
    }

    @RequestMapping("/env/from")
    public String fromEnv(){
        return env.getProperty("from", "undefined");
    }

}
