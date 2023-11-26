package dk.pejomi.authservice.service.impl;

import dk.pejomi.authservice.model.LoginDto;
import dk.pejomi.authservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    private User user;
    private LoginDto loginDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .username("John")
                .email("test@mail.dk")
                .password("password")
                .build();
        loginDto = LoginDto.builder()
                .username("John")
                .password("Password")
                .build();
    }
    @Test
    void notNull() {
        assertNotNull(user);
    }


}