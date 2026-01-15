package com.example.mapper;

import com.example.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    int insert(Article article);

    Article selectById(Integer id);

    List<Article> selectAll();

    int update(Article article);

    int delete(Integer id);

    // ⭐ 分页 + 搜索 + 多表关联
    List<Article> selectPageWithUser(@Param("keyword") String keyword);

    Article selectByIdWithUser(Integer id);

}
