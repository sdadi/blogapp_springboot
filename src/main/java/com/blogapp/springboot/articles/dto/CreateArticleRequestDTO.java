package com.blogapp.springboot.articles.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequestDTO {
    @NonNull
    private String title;
    @NonNull
    private String body;
    @Nullable
    private String subtitle;
}
