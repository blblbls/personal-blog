package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {

        User user = userMapper.selectByUsernameAndPassword(username, password);

// 简单校验
        if (username == null || password == null) {
            return null;
        }

        return userMapper.selectByUsernameAndPassword(username, password);
    }
}
