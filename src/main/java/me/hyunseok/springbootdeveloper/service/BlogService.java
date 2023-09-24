package me.hyunseok.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.hyunseok.springbootdeveloper.domain.Article;
import me.hyunseok.springbootdeveloper.dto.AddArticleRequest;
import me.hyunseok.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 전체 글 조회
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // 특정 글 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 특정 글 삭제
    public void delete(long id) {
        blogRepository.deleteById(id);
    }
}
