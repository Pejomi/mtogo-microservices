package dk.pejomi.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.dto.OrderItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class OrderControllerIT {

    private final MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderDto orderDto;

    @BeforeEach
    public void init() {
        orderDto = OrderDto.builder()
                .consumerId(1L)
                .restaurantId(1L)
                .build();
    }

    @Autowired
    public OrderControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void should_return_orderDto_when_creating_order() throws Exception {
        // Arrange
        orderDto.setOrderItemsDto(List.of(
                OrderItemDto.builder()
                        .menuItemId(1000L)
                        .price(90)
                        .quantity(1)
                        .build(),
                OrderItemDto.builder()
                        .menuItemId(1001L)
                        .price(60)
                        .quantity(1)
                        .build()
        ));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.restaurantId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderState").value("CREATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(150));
    }

    @Test
    void should_return_bad_request_when_creating_order_with_price_below_minimum() throws Exception {
        // Arrange
        orderDto.setOrderItemsDto(List.of(
                OrderItemDto.builder()
                        .menuItemId(1000L)
                        .price(90)
                        .quantity(1)
                        .build()
        ));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}