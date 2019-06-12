package com.yqyan.portal.web;

import com.yqyan.portal.service.UserService;
import com.yqyan.portal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author yanyaqiang
 * @Date 2019/5/14 14:37
 **/
@RestController
public class PortalController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/listAll")
    public List<User> listAllUsers(){
        return userService.listAllUsers();
    }

    @GetMapping("/user/listAll1")
    public List<User> listAllUsers1(@RequestParam Boolean async) {
        return userService.listAllUsersCommand(async);
    }

    @GetMapping("/user/listAll2")
    public List<User> listAllUsers2(@RequestParam Boolean immediate) {
        return userService.listAllUsersObservableCommand(immediate);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable("id") Integer id){
        return userService.findUserById(id);
    }

    @PostMapping("/user/add")
    public Object addUser(@RequestBody Map<String, Object> user){
        return userService.addUser(user);
    }

}
