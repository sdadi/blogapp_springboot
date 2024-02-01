package com.blogapp.springboot.articles;

import com.blogapp.springboot.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Entity(name = "articles")
@RequiredArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "authorId",nullable = false)
    private UserEntity author;

}
