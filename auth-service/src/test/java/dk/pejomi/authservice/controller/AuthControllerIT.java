package dk.pejomi.authservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.authservice.model.AuthResponseDto;
import dk.pejomi.authservice.model.LoginDto;
import dk.pejomi.authservice.model.RegisterConsumerDto;
import dk.pejomi.authservice.model.RegisterRestaurantDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class AuthControllerIT {

    private final MockMvc mockMvc;

    @Autowired
    public AuthControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    private ObjectMapper objectMapper;

    private LoginDto loginDto;
    private RegisterConsumerDto registerConsumerDto;
    private RegisterRestaurantDto registerRestaurantDto;
    private AuthResponseDto authResponseDto;

    @BeforeEach
    public void init() throws Exception {
        loginDto = LoginDto.builder()
                .email("test@email.dk")
                .password("password")
                .build();
        registerConsumerDto = RegisterConsumerDto.builder()
                .email("test@email.dk")
                .password("password")
                .phone("12345678")
                .street("Street 1")
                .city("Copenhagen")
                .zipCode("1234")
                .country("Denmark")
                .build();

//        registerRestaurantDto = RegisterRestaurantDto.builder()
//                .email("test@mail.dk")
//                .password("password")
//                .phone("12345678")
//                .street("street")
//                .city("city")
//                .zipCode("zipCode")
//                .country("country")
//                .homepage("www.homepage.dk")
//                .restaurantState("PENDING")
//                .build();
//
//        authResponseDto = new AuthResponseDto("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNb3J0ZW4iLCJpYXQiOjE3MDA5MzU2MjUsImV4cCI6MTcwMDkzNTY5NX0.SRYdU2a01hOqWsHNO7brGW5cMsKeytNl3opUrODc_Tb2UDOTKM0qN6TyMIJ8Ibcs9WCE31Vdb3HyxR7KdzRI3Q");
    }

    @Test
    void should_return_ok_when_login_with_valid_credentials() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register/consumer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registerConsumerDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        //fixme


//        mockMvc.perform(post("/api/auth/login")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(loginDto)))
//                .andExpect(status().isOk());
    }

//    @Test
//    void should_return_unauthorized_when_login_with_invalid_credentials() throws Exception {
//        assert true;
//    }
//
//    @Test
//    void should_return_bad_request_when_registering_with_existing_email() throws Exception {
//        assert true;
//    }

}
