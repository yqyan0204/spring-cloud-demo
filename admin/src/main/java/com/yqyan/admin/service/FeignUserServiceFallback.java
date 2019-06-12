package com.yqyan.admin.service;

import com.yqyan.model.User;
import com.yqyan.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yanyaqiang
 * @Date 2019/6/12 11:47
 **/
//@Component
public class FeignUserServiceFallback implements FeignUserService {
    @Override
    public List<User> listAllUsers() {
        return new ArrayList<>();
    }

    @Override
    public User findUserById(Integer id) {
        return new User();
    }

    @Override
    public User addUser(User user) {
        return new User();
    }
}
