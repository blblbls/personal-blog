package com.example.test;

import com.example.pojo.Article;
import com.example.service.ArticleService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ArticleTest {

    @Test
    public void testArticleCRUD() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        ArticleService articleService = context.getBean(ArticleService.class);

        // 1️⃣ 新增
        Article article = new Article();
        article.setTitle("我的第一篇文章");
        article.setContent("这是文章内容");
        article.setUserId(1);
        articleService.addArticle(article);

        // 2️⃣ 查询全部
        List<Article> list = articleService.getAllArticles();
        System.out.println("文章数量：" + list.size());

        // 3️⃣ 查询单个
        Article one = articleService.getArticleById(list.get(0).getId());
        System.out.println(one.getTitle());

        // 4️⃣ 修改
        one.setTitle("修改后的标题");
        articleService.updateArticle(one);

        // 5️⃣ 删除
        articleService.deleteArticle(one.getId());
    }
}
