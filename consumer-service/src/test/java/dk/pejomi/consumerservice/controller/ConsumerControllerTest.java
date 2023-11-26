package dk.pejomi.consumerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.consumerservice.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsumerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsumerService consumerService;

    @Autowired
    private ObjectMapper objectMapper;

    private ConsumerDto consumerDTO;

    @BeforeEach
    void setUp() {
        consumerDTO = ConsumerDto.builder()
                .email("john@doe.com")
                .phone("12345678")
                .city("Copenhagen")
                .country("Denmark")
                .street("Street 1")
                .zipCode("1234")
                .build();
    }

    @Test
    void should_return_consumerDTO_when_creating_consumer() throws Exception {
        // Arrange
        when(consumerService.createConsumer(consumerDTO)).thenReturn(consumerDTO);

        // Act
        ResultActions response = mockMvc.perform(post("/api/consumers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(consumerDTO)));

        // Assert
        response.andExpect(status().isCreated())
                .andExpect(result -> {
                    ConsumerDto consumer = objectMapper.readValue(result.getResponse().getContentAsString(), ConsumerDto.class);
                    assertEquals(consumerDTO, consumer);
                });
    }
}