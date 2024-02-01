package com.blogapp.springboot.security;

import com.blogapp.springboot.users.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

//@Configuration
//@EnableWebSecurity
//public class AppConfigSecurity extends WebSecurityConfigurerAdapter {
//    private JWTAuthenticationFilter jwtAuthenticationFilter;
//    private JWTService jwtService;
//    private UserService userService;
//
//    public AppConfigSecurity(JWTAuthenticationFilter jwtAuthenticationFilter, JWTService jwtService, UserService userService){
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//        this.jwtService = jwtService;
//        this.userService = userService;
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
////                .antMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
////                .antMatchers(HttpMethod.GET, "/articles", "/articles/*").permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
//
//    }
//}


@Configuration
public class AppConfigSecurity {
    private final JWTAuthenticationManager jwtAuthenticationManager;

    public AppConfigSecurity(JWTAuthenticationManager jwtAuthenticationManager) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.POST, "users/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "users/").permitAll()
                        .requestMatchers(HttpMethod.GET, "articles").permitAll()
                        .anyRequest().authenticated()
                )
                .authenticationManager(jwtAuthenticationManager);
//                .httpBasic(withDefaults());
        return http.build();
    }

}