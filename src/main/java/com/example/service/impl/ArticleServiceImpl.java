package com.example.service.impl;

import com.example.mapper.ArticleMapper;
import com.example.pojo.Article;
import com.example.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    // ✅ 只保留这一份
    @Resource
    private ArticleMapper articleMapper;

    // 新增文章
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addArticle(Article article) {
        articleMapper.insert(article);
    }

    // 根据 id 查询
    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectById(id);
    }

    // 查询全部（非分页时用）
    @Override
    public List<Article> getAllArticles() {
        return articleMapper.selectAll();
    }

    // 更新文章
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(Article article) {
        articleMapper.update(article);
    }

    // 删除文章（事务 + 校验）
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Integer id) {

        if (id == null) {
            throw new RuntimeException("参数错误：id 不能为空");
        }

        int rows = articleMapper.delete(id);

        if (rows == 0) {
            throw new RuntimeException("删除失败，文章不存在");
        }
    }

    // ⭐⭐⭐ 分页查询（关键）
    @Override
    public PageInfo<Article> getArticlePage(int pageNum, int pageSize) {

        // 一定在查询前
        PageHelper.startPage(pageNum, pageSize);

        List<Article> list = articleMapper.selectAll();

        return new PageInfo<>(list);
    }
    @Override
    public PageInfo<Article> getArticlePage(int pageNum, int pageSize, String keyword) {

        PageHelper.startPage(pageNum, pageSize);

        List<Article> list = articleMapper.selectPageWithUser(keyword);

        return new PageInfo<>(list);
    }


}
