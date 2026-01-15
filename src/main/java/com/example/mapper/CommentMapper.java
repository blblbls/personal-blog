package com.example.mapper;

import com.example.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    List<Comment> selectByArticleId(Integer articleId);

    int insert(Comment comment);
    // 根据评论 id 删除
    int deleteById(@Param("id") int id);

    // ⭐ 查询单条评论（用于权限判断）
    Comment selectById(Integer id);
}
