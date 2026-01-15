package com.example.service;

import com.example.pojo.Comment;
import com.example.pojo.User;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByArticle(Integer articleId);

    void add(Comment comment);

    // ⭐ 带权限的删除
    void deleteById(Integer commentId, User loginUser);
}
