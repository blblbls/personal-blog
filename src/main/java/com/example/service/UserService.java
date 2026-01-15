package com.example.service;

import com.example.pojo.User;

public interface UserService {

    /**
     * 用户登录
     */
    User login(String username, String password);
}
