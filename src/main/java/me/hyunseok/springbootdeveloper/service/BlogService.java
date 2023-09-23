package me.hyunseok.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.hyunseok.springbootdeveloper.domain.Article;
import me.hyunseok.springbootdeveloper.dto.AddArticleRequest;
import me.hyunseok.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
