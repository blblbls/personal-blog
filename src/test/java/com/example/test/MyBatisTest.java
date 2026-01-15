package com.example.test;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBatisTest {

    @Test
    public void testMyBatis() {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        UserMapper userMapper = context.getBean(UserMapper.class);

        User user = userMapper.selectByUsernameAndPassword("admin", "123456");

        System.out.println(user.getUsername());
    }
}
