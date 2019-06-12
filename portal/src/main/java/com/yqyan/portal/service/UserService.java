package com.yqyan.portal.service;

import com.yqyan.portal.model.User;

import java.util.List;
import java.util.Map;

/**
 * @Author yanyaqiang
 * @Date 2019/5/21 10:17
 **/
public interface UserService {

    List<User> listAllUsers();

    List<User> listAllUsersCommand(Boolean async);

    List<User> listAllUsersObservableCommand(Boolean immediate);

    User findUserById(Integer id);

    Object addUser(Map<String, Object> user);
}
