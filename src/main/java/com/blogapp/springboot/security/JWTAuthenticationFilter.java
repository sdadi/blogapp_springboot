package com.blogapp.springboot.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JWTAuthenticationFilter extends AuthenticationFilter {

    public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager){
        super(jwtAuthenticationManager, new JWTAuthenticationConverter());

        this.setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }
}
