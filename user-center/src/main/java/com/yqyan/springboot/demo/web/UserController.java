package com.yqyan.springboot.demo.web;

import com.yqyan.springboot.demo.common.Constants;
import com.yqyan.springboot.demo.dao.UserMapper;
import com.yqyan.springboot.demo.model.User;
import com.yqyan.springboot.demo.model.UserExample;
import com.yqyan.springboot.demo.utils.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yanyaqiang
 * @Date 2019/4/26 09:54
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Constants constants;

    @Autowired
    private DiscoveryClient client;


    @GetMapping("/listAll")
    public List<User> listAllUsers(){
        return userMapper.selectByExample(null);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    @GetMapping("/query")
    public List<User> findUserByCritiea(){
        UserExample userExample =  new UserExample();
        userExample.or()
                .andAgeGreaterThanOrEqualTo(10)
                .andGenderEqualTo((byte) 1);
        return userMapper.selectByExample(userExample);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        ParamValidator.check(user);

        userMapper.insert(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }


}
