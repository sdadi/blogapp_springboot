package com.blogapp.springboot.security;

import com.blogapp.springboot.users.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;

public class JWTAuthenticationManager implements AuthenticationManager {
    private final JWTService jwtService;
    private final UserService userService;

    public JWTAuthenticationManager(JWTService jwtService,UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }
@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (authentication instanceof JWTAuthentication jwtAuthentication) {
        var jwt = jwtAuthentication.getCredentials();
        var userId = jwtService.retriveUserId(jwt);

        jwtAuthentication.userEntity = userService.getUser(userId);
        jwtAuthentication.setAuthenticated(true);
        return jwtAuthentication;
    }
    throw new IllegalAccessError("JWT token is missing or authentication failed");
}
}
