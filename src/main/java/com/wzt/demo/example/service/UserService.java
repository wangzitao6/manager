package com.wzt.demo.example.service;

import com.wzt.demo.example.entity.User;

import java.util.List;

/**
 * @author wangzitao
 * @create 2018-05-17 0:12
 **/

public interface UserService {
    int addUser(User user);

    User selectUserById(Integer id);

    List<User> findAllUser();
}
