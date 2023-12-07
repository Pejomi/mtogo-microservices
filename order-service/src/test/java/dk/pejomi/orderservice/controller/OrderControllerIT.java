package dk.pejomi.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.OrderItemDto;
import dk.pejomi.orderservice.service.OrderService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderDto orderDto;
    private List<OrderItemDto> orderItemDtos;

    @BeforeEach
    public void init() {
        orderItemDtos = List.of(
                OrderItemDto.builder()
                        .menuItemId(1L)
                        .price(100)
                        .quantity(2)
                        .build(),
                OrderItemDto.builder()
                        .menuItemId(2L)
                        .price(50)
                        .quantity(1)
                        .build()
        );


        orderDto = OrderDto.builder()
                .consumerId(1L)
                .restaurantId(1L)
                .orderState("PENDING")
                .price(1000)
                .orderItems(orderItemDtos)
                .build();
    }

    @Test
    void should_return_orderDto_when_creating_order() throws Exception {
        // Arrange
        when(orderService.createOrder(orderDto)).thenReturn(orderDto);

        // Act
        ResultActions response = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        // Assert
        response.andExpect(status().isCreated())
                .andExpect(result -> assertEquals(orderDto, objectMapper.readValue(result.getResponse().getContentAsString(), OrderDto.class)));
    }

}