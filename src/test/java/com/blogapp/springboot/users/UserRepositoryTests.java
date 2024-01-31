package com.blogapp.springboot.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void can_create_users(){
        var user = UserEntity.builder()
                .username("Satish")
                .email("satish@blog.com")
                .build();
        userRepository.save(user);
    }
}
