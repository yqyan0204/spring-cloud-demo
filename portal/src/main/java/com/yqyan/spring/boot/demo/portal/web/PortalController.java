package com.yqyan.spring.boot.demo.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Author BG388812
 * @Date 2019/5/14 14:37
 **/
@RestController
public class PortalController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_CENTER_URL_PREFIX = "http://USER-CENTER/user/";

    @GetMapping("/user/listAll")
    public List<Object> listAllUsers(){
        return restTemplate.getForEntity(USER_CENTER_URL_PREFIX + "listAll", List.class).getBody();
    }

    @GetMapping("/user/{id}")
    public Object findUserById(@PathVariable("id") Integer id){
        return restTemplate.getForObject(USER_CENTER_URL_PREFIX + "{1}", Object.class, id);
    }

    @PostMapping("/user/add")
    public Object addUser(@RequestBody Map<String, Object> user){
        return restTemplate.postForObject(USER_CENTER_URL_PREFIX + "add", user, Object.class);
    }

}
