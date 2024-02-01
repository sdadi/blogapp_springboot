package com.blogapp.springboot.users.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String image;
    private String token;
}
