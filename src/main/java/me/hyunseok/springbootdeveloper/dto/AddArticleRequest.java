package me.hyunseok.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.hyunseok.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    // DTO to entity
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}