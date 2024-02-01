package com.blogapp.springboot.users.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginUserRequestDTO {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
