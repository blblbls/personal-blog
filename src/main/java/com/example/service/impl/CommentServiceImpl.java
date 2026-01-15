package com.example.service.impl;

import com.example.mapper.CommentMapper;
import com.example.pojo.Comment;
import com.example.pojo.User;
import com.example.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByArticle(Integer articleId) {
        return commentMapper.selectByArticleId(articleId);
    }

    @Override
    public void add(Comment comment) {
        commentMapper.insert(comment);
    }

    /**
     * ⭐ 删除评论（权限控制）
     */
    @Override
    public void deleteById(Integer commentId, User loginUser) {

        Comment comment = commentMapper.selectById(commentId);
        if (comment == null) {
            throw new RuntimeException("评论不存在");
        }

        // 管理员可以删所有
        if ("ADMIN".equals(loginUser.getRole())) {
            commentMapper.deleteById(commentId);
            return;
        }

        // 普通用户只能删自己的
        if (comment.getUserId().equals(loginUser.getId())) {
            commentMapper.deleteById(commentId);
            return;
        }

        // 其他情况
        throw new RuntimeException("无权删除该评论");
    }
}
