package com.example.controller;

import com.example.pojo.Comment;
import com.example.pojo.User;
import com.example.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 添加评论
     */
    @PostMapping("/comment/add")
    public String add(Comment comment, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("请先登录后再发表评论");
        }

        comment.setUserId(loginUser.getId());

        commentService.add(comment);

        return "redirect:/article/detail?id=" + comment.getArticleId();
    }

    /**
     * 删除评论
     */
    @PostMapping("/comment/delete")
    public String delete(
            @RequestParam("id") Integer id,
            @RequestParam("articleId") Integer articleId,
            HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new RuntimeException("请先登录");
        }

        commentService.deleteById(id, loginUser);

        return "redirect:/article/detail?id=" + articleId;
    }
}
