package com.yqyan.admin.service;

import com.yqyan.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author yanyaqiang
 * @Date 2019/6/6 17:33
 **/
//@FeignClient("user-center")
public interface UserService {
    @RequestMapping("/user/listAll")
    List<User> listAllUsers();

    @GetMapping("/user/{id}")
    User findUserById(@PathVariable("id") Integer id);

    @PostMapping("/user/add")
    User addUser(@RequestBody User user);

}
