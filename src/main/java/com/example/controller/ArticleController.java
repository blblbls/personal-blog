package com.example.controller;

import com.example.pojo.Article;
import com.example.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.service.CommentService;
import java.util.List;
import com.example.pojo.Comment;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;

    /**
     * 文章列表（分页 + 搜索 + CSRF Token）
     */
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(required = false) String keyword,
            Model model,
            HttpSession session) {

        PageInfo<Article> pageInfo =
                articleService.getArticlePage(pageNum, 5, keyword);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keyword", keyword);

        // ⭐ 生成 CSRF Token（session 级）
        if (session.getAttribute("csrfToken") == null) {
            session.setAttribute("csrfToken", UUID.randomUUID().toString());
        }

        return "article-list";
    }

    /**
     * 新增页面
     */
    @GetMapping("/add")
    public String addPage() {
        return "article-add";
    }

    /**
     * 提交新增
     */
    @PostMapping("/add")
    public String add(Article article) {
        articleService.addArticle(article);
        return "redirect:/article/list";
    }

    /**
     * 删除文章（POST + CSRF + 保持页码 + 删空跳前一页）
     */
    @PostMapping("/delete")
    public String delete(
            Integer id,
            Integer pageNum,
            String keyword,
            String csrfToken,
            HttpSession session,
            RedirectAttributes ra) {

        // ⭐ pageNum 兜底
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }

        // ⭐ CSRF 校验
        String sessionToken = (String) session.getAttribute("csrfToken");
        if (sessionToken == null || !sessionToken.equals(csrfToken)) {
            ra.addFlashAttribute("error", "非法请求（CSRF 校验失败）");
            return "redirect:/article/list";
        }

        try {
            articleService.deleteArticle(id);
            ra.addFlashAttribute("success", "删除成功");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
        }

        // ⭐ 判断是否删空当前页
        PageInfo<Article> pageInfo =
                articleService.getArticlePage(pageNum, 5, keyword);

        if (pageInfo.getList().isEmpty() && pageNum > 1) {
            pageNum--;
        }

        return "redirect:/article/list"
                + "?pageNum=" + pageNum
                + "&keyword=" + (keyword == null ? "" : keyword);
    }

    /**
     * 修改页面
     */
    @GetMapping("/edit")
    public String edit(Integer id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article-edit";
    }

    /**
     * 提交修改（保持页码 + 搜索条件）
     */
    @PostMapping("/update")
    public String update(
            Article article,
            Integer pageNum,
            String keyword) {

        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }

        articleService.updateArticle(article);

        return "redirect:/article/list"
                + "?pageNum=" + pageNum
                + "&keyword=" + (keyword == null ? "" : keyword);
    }
    @GetMapping("/detail")
    public String detail(Integer id, Model model) {

        Article article = articleService.getArticleById(id);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }

        // ⭐ 查询评论（三表）
        List<Comment> comments = commentService.getCommentsByArticle(id);

        model.addAttribute("article", article);
        model.addAttribute("comments", comments);

        return "article-detail";
    }

}
