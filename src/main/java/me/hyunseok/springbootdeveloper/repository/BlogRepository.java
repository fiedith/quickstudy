package me.hyunseok.springbootdeveloper.repository;

import me.hyunseok.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
