package com.example.service;

import com.example.pojo.Article;
import java.util.List;
import com.github.pagehelper.PageInfo;

public interface ArticleService {
    PageInfo<Article> getArticlePage(int pageNum, int pageSize);
    PageInfo<Article> getArticlePage(int pageNum, int pageSize, String keyword);

    void addArticle(Article article);

    Article getArticleById(Integer id);

    List<Article> getAllArticles();

    void updateArticle(Article article);

    void deleteArticle(Integer id);


}
