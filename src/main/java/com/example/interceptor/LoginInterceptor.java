package com.example.interceptor;

import com.example.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession();

        // 从 session 中取登录用户
        User loginUser = (User) session.getAttribute("loginUser");

        // 已登录，放行
        if (loginUser != null) {
            return true;
        }

        // 未登录，重定向到登录页
        response.sendRedirect(
                request.getContextPath() + "/user/login"
        );

        return false;
    }
}
