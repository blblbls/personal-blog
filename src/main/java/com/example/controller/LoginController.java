package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 跳转登录页
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * 处理登录
     */
    @PostMapping("/login")
    public String login(
            String username,
            String password,
            HttpSession session,
            Model model) {

        User user = userService.login(username, password);

        if (user == null) {
            model.addAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }

        session.setAttribute("loginUser", user);
        return "redirect:/article/list";
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
