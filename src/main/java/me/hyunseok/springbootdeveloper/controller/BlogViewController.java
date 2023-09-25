package me.hyunseok.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.hyunseok.springbootdeveloper.dto.ArticleListViewResponse;
import me.hyunseok.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * thymeleaf (view)
 */
@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);

        // resources/templates/articleList.html
        return "articleList";
    }
}