package com.blogapp.springboot.comments;

import java.util.Date;

import com.blogapp.springboot.articles.ArticleEntity;
import com.blogapp.springboot.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity(name = "comments")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private  Long id;

    @Nullable
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private Date CreatedDate;

    @ManyToOne
    @JoinColumn(name="articleId", nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    private UserEntity author;
}
