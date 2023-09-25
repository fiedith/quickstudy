package me.hyunseok.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hyunseok.springbootdeveloper.domain.Article;

import java.time.LocalDateTime;

/**
 * thymeleaf (view)
 */
@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
