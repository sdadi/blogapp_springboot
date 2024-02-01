package com.blogapp.springboot.articles;

import com.blogapp.springboot.articles.dto.CreateArticleRequestDTO;
import com.blogapp.springboot.articles.dto.UpdateArticleRequestDTO;
import com.blogapp.springboot.users.UserRepository;
import com.blogapp.springboot.users.UserService;
import com.blogapp.springboot.users.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private  final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository,UserRepository userRepository){
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }
    public Iterable<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }
    public ArticleEntity getArticleBySlug(String slug) {
        var article = articleRepository.findBySlug(slug);
        if (article == null) {
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }
    public ArticleEntity getArticleById(Long id){
        var article = articleRepository.findById(id);
        if(article == null) throw new ArticleNotFoundException(id);
        return article;
    }

    public ArticleEntity createArticle(CreateArticleRequestDTO a, Long authorId) {
        var author = userRepository.findById(authorId).orElseThrow(() -> new UserService.UserNotFoundException(authorId));

        return articleRepository.save(ArticleEntity.builder()
                .title(a.getTitle())
                // TODO: create a proper slugification function
                .slug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                .body(a.getBody())
                .subtitle(a.getSubtitle())
                .author(author)
                .build()
        );
    }
    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequestDTO a) {
        var article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));

        if (a.getTitle() != null) {
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }

        if (a.getBody() != null) {
            article.setBody(a.getBody());
        }

        if (a.getSubtitle() != null) {
            article.setSubtitle(a.getSubtitle());
        }

        return articleRepository.save(article);
    }
    static class ArticleNotFoundException extends IllegalArgumentException {
        public ArticleNotFoundException(String slug) {
            super("Article " + slug + " not found");
        }

        public ArticleNotFoundException(Long id) {
            super("Article with id: " + id + " not found");
        }
    }
}
