package com.yqyan.user.center.web;

import com.yqyan.model.User;
import com.yqyan.user.center.common.Constants;
import com.yqyan.user.center.dao.UserMapper;
import com.yqyan.user.center.model.UserExample;
import com.yqyan.user.center.utils.ParamValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @Author yanyaqiang
 * @Date 2019/4/26 09:54
 **/

//@RestController
//@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Constants constants;


    @GetMapping("/listAll")
    public List<User> listAllUsers(){
        randomSleep(4000);
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

    private void randomSleep(int maxSleepTime){
        Random rand = new Random();
        int sleepTime = rand.nextInt(maxSleepTime);
        LOGGER.info("sleep time: {}", sleepTime);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            LOGGER.error("sleep interrupted", e);
        }

    }


}
