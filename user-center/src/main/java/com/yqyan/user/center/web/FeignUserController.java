package com.yqyan.user.center.web;

import com.yqyan.model.User;
import com.yqyan.user.api.service.UserService;
import com.yqyan.user.center.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author yanyaqiang
 * @Date 2019/6/10 19:45
 **/

@RestController
public class FeignUserController implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignUserController.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listAllUsers() {

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("user-center controller listAllUsers sleep 2000 ms");
        return userMapper.selectByExample(null);
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User addUser(User user) {
        userMapper.insert(user);
        return user;
    }
}
