package com.yqyan.admin.web;

import com.yqyan.admin.service.FeignUserService;
import com.yqyan.admin.service.UserService;
import com.yqyan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yanyaqiang
 * @Date 2019/6/6 17:35
 **/

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
//    private UserService userService;
    private FeignUserService userService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public List<User> listAllUsers(){
        return userService.listAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") Integer id){
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public User addUser(){
        User user = new User(null, "test", 11, (byte) 1, "hangzhou");
        return userService.addUser(user);
    }

}
