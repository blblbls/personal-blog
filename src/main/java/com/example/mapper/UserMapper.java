package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password
    );
}
