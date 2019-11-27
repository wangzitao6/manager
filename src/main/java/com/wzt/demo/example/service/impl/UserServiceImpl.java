package com.wzt.demo.example.service.impl;

import com.wzt.demo.example.dao.UserMapper;
import com.wzt.demo.example.entity.User;
import com.wzt.demo.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzitao
 * @create 2018-05-17 0:38
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {

        return userMapper.insert(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.selectAllUser();
    }

}
