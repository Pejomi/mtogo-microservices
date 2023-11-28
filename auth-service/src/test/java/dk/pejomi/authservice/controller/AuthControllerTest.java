package dk.pejomi.authservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.authservice.model.AuthResponseDto;
import dk.pejomi.authservice.model.LoginDto;
import dk.pejomi.authservice.model.RegisterDto;
import dk.pejomi.authservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private LoginDto loginDto;
    private RegisterDto registerDto;
    private AuthResponseDto authResponseDto;

    @BeforeEach
    public void init() {
        loginDto = LoginDto.builder()
                .email("test@email.dk")
                .password("password")
                .build();
        registerDto = RegisterDto.builder()
                .email("test@email.dk")
                .password("password")
                .phone("12345678")
                .street("Street 1")
                .city("Copenhagen")
                .zipCode("1234")
                .country("Denmark")
                .build();
        authResponseDto = new AuthResponseDto("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNb3J0ZW4iLCJpYXQiOjE3MDA5MzU2MjUsImV4cCI6MTcwMDkzNTY5NX0.SRYdU2a01hOqWsHNO7brGW5cMsKeytNl3opUrODc_Tb2UDOTKM0qN6TyMIJ8Ibcs9WCE31Vdb3HyxR7KdzRI3Q");

    }

    @Test
    void should_return_200_when_login_successfully() throws Exception {
        // Arrange
        when(userService.login(any(LoginDto.class))).thenReturn(authResponseDto);

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_400_when_login_with_wrong_cred() throws Exception {
        // Arrange
        when(userService.login(any(LoginDto.class))).thenThrow(new BadCredentialsException("Invalid username/password supplied"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void should_return_400_when_consumer_register_with_existing_username() throws Exception {
        // Arrange
        when(userService.checkEmail(any(String.class))).thenReturn(true);

        mockMvc.perform(post("/api/auth/register/consumer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void should_return_201_when_consumer_register_successfully() throws Exception {
        // Arrange
        when(userService.checkEmail(any(String.class))).thenReturn(false);
        when(userService.registerConsumer(any(RegisterDto.class))).thenReturn("User registered successfully");

        mockMvc.perform(post("/api/auth/register/consumer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_return_500_when_consumer_register_with_exception() throws Exception {
        // Arrange
        when(userService.checkEmail(any(String.class))).thenReturn(false);
        when(userService.registerConsumer(any(RegisterDto.class))).thenThrow(new RuntimeException("Error registering user"));

        mockMvc.perform(post("/api/auth/register/consumer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void should_return_400_when_restaurant_register_with_existing_username() throws Exception {
        // Arrange
        when(userService.checkEmail(any(String.class))).thenReturn(true);

        mockMvc.perform(post("/api/auth/register/restaurant")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_201_when_restaurant_register_successfully() throws Exception {
        // Arrange
        when(userService.checkEmail(any(String.class))).thenReturn(false);
        when(userService.registerRestaurant(any(RegisterDto.class))).thenReturn("User registered successfully");

        mockMvc.perform(post("/api/auth/register/restaurant")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_return_500_when_restaurant_register_with_exception() throws Exception {
        // Arrange
        when(userService.checkEmail(any(String.class))).thenReturn(false);
        when(userService.registerRestaurant(any(RegisterDto.class))).thenThrow(new RuntimeException("Error registering user"));

        mockMvc.perform(post("/api/auth/register/restaurant")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isInternalServerError());
    }



}