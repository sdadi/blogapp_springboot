package com.blogapp.springboot.articles;

import com.blogapp.springboot.users.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String title;

    @NonNull
    @Column(unique = true)
    private String slug;

    @Nullable
    @Column(nullable = true)
    private String subtitle;

    @NonNull
    @Column(nullable = false)
    private String body;

    @CreatedDate
    @NonNull
    private Date CreatedDate;

    @ManyToOne
    @JoinColumn(name = "author_Id",nullable = false)
    private UserEntity author;

}
