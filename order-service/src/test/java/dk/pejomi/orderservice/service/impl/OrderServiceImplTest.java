package dk.pejomi.orderservice.service.impl;

import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.event.OrderEvent;
import dk.pejomi.orderservice.dto.OrderItemDto;
import dk.pejomi.orderservice.kafka.OrderProducer;
import dk.pejomi.orderservice.model.Order;
import dk.pejomi.orderservice.model.OrderItem;
import dk.pejomi.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderProducer orderProducer;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private OrderDto orderDto;

    private List<OrderItem> orderItems;
    private List<OrderItemDto> orderItemDtos;

    private OrderEvent orderEvent;

    @BeforeEach
    void setUp() {
        orderItemDtos = List.of(
                OrderItemDto.builder()
                        .id(1L)
                        .menuItemId(1L)
                        .price(100)
                        .quantity(2)
                        .build(),
                OrderItemDto.builder()
                        .id(2L)
                        .menuItemId(2L)
                        .price(50)
                        .quantity(1)
                        .build()
        );

        orderItems = List.of(
                OrderItem.builder()
                        .id(1L)
                        .menuItemId(1L)
                        .price(100)
                        .quantity(2)
                        .build(),
                OrderItem.builder()
                        .id(2L)
                        .menuItemId(2L)
                        .price(50)
                        .quantity(1)
                        .build()
        );
        orderDto = OrderDto.builder()
                .id(1L)
                .consumerId(1L)
                .restaurantId(1L)
                .orderState("PENDING")
                .price(250)
                //.orderItems(orderItemDtos)
                .build();

        order = Order.builder()
                .id(1L)
                .consumerId(1L)
                .restaurantId(1L)
                .orderState("PENDING")
                .price(250)
                .orderItems(orderItems)
                .build();

        orderEvent = OrderEvent.builder()
                .message("order status is in pending state")
                .status("PENDING")
                .orderDto(orderDto)
                .build();
    }

    @Test
    void should_return_orderDto_when_order_is_created() throws Exception {
        // Arrange
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        when(orderProducer.sendMessage(Mockito.any(OrderEvent.class))).thenReturn(orderEvent);

        // when
        OrderDto actual = orderService.createOrder(orderDto);

        // then
        assertEquals(orderDto.getId(), actual.getId());
        assertEquals(orderDto.getConsumerId(), actual.getConsumerId());
        assertEquals(orderDto.getRestaurantId(), actual.getRestaurantId());
        assertEquals(orderDto.getOrderState(), actual.getOrderState());
        assertEquals(orderDto.getPrice(), actual.getPrice());
        //assertEquals(orderDto.getOrderItems().size(), actual.getOrderItems().size());
    }


}