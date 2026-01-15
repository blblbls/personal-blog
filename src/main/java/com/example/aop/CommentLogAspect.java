package com.example.aop;

import com.example.pojo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommentLogAspect {

    /**
     * 删除评论成功后的日志
     */
    @AfterReturning(
            value = "execution(* com.example.service.CommentService.deleteById(..))"
    )
    public void logDeleteComment(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Integer commentId = (Integer) args[0];
        User loginUser = (User) args[1];

        System.out.println(
                "[AOP] 用户 "
                        + loginUser.getUsername()
                        + "(id=" + loginUser.getId() + ") "
                        + "删除了评论 id=" + commentId
        );
    }
}
