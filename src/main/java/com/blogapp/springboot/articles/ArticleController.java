package com.blogapp.springboot.articles;

import com.blogapp.springboot.articles.dto.CreateArticleRequestDTO;
import com.blogapp.springboot.users.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }
    @GetMapping("/{slug}")
    ResponseEntity<ArticleEntity> getArticleBySlug(@PathVariable("slug") String slug) {
        var article = articleService.getArticleBySlug(slug);
        return ResponseEntity.ok(article);
    }
    @GetMapping("/{id}")
    ResponseEntity<ArticleEntity> getArticleById(@PathVariable("id") Long id){
        var article = articleService.getArticleById(id);
        return ResponseEntity.ok(article);
    }

    @PostMapping("")
    ResponseEntity<ArticleEntity> createArticle(@AuthenticationPrincipal UserEntity user, @RequestBody CreateArticleRequestDTO request) {
        var article = articleService.createArticle(request,user.getId());
        return ResponseEntity.ok(article);
    }
}
