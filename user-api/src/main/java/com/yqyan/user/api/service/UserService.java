package com.yqyan.user.api.service;

import com.yqyan.model.User;
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
@RequestMapping("/user")
public interface UserService {
    @GetMapping("/listAll")
    List<User> listAllUsers();

    @GetMapping("/{id}")
    User findUserById(@PathVariable("id") Integer id);

    @PostMapping("/add")
    User addUser(@RequestBody User user);

}
