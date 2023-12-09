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
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class OrderControllerIT {

    private final MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderDto orderDto;

    @BeforeEach
    public void init() {
        List<OrderItemDto> orderItemDtos = List.of(
                OrderItemDto.builder()
                        .menuItemId(1000L)
                        .price(100)
                        .quantity(2)
                        .build(),
                OrderItemDto.builder()
                        .menuItemId(1001L)
                        .price(50)
                        .quantity(1)
                        .build()
        );
        orderDto = OrderDto.builder()
                .id(1000L)
                .consumerId(1L)
                .restaurantId(1L)
                .orderState("CREATED")
                .price(1000)
                .orderItemsDto(orderItemDtos)
                .build();
    }

    @Autowired
    public OrderControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void should_return_orderDto_when_creating_order() throws Exception {
        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.restaurantId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderState").value("CREATED"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
    }

}