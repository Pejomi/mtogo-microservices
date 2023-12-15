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

    @BeforeEach
    void setUp() {
        List<OrderItemDto> orderItemDtos = List.of(
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

        List<OrderItem> orderItems = List.of(
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
                .orderState("CREATED")
                .orderItemsDto(orderItemDtos)
                .price(250)
                .build();

        order = Order.builder()
                .id(1L)
                .consumerId(1L)
                .restaurantId(1L)
                .orderState("CREATED")
                .price(250)
                .orderItems(orderItems)
                .build();
    }

    @Test
    void should_return_orderDto_when_order_is_created() throws Exception {
        // Arrange
        when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(order);
        when(orderProducer.sendMessage(Mockito.any(OrderEvent.class)))
                .thenReturn(new OrderEvent());
        // Act
        OrderDto actual = orderService.createOrder(orderDto);
        // Assert
        assertEquals(orderDto, actual);
    }

    @Test
    void should_throw_exception_when_order_price_is_below_minimum() {
        // Arrange
        orderDto.setOrderItemsDto(List.of(
                OrderItemDto.builder()
                        .menuItemId(1000L)
                        .price(90)
                        .quantity(1)
                        .build()
        ));
        // Act
        RuntimeException exception = assertThrows(
                RuntimeException.class, () -> orderService.createOrder(orderDto));
        // Assert
        assertEquals("Order price is below minimum", exception.getMessage());
    }


}