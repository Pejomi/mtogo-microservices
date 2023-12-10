package dk.pejomi.orderservice.controller;

import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void testCreateOrder_Success() {
        // Arrange
        OrderDto inputOrderDto = new OrderDto(/* provide necessary details */);
        OrderDto expectedOrderDto = new OrderDto(/* expected details after creation */);

        when(orderService.createOrder(inputOrderDto)).thenReturn(expectedOrderDto);

        // Act
        ResponseEntity<OrderDto> responseEntity = orderController.createOrder(inputOrderDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedOrderDto, responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_Failure() {
        // Arrange
        OrderDto inputOrderDto = new OrderDto(/* provide necessary details */);

        when(orderService.createOrder(inputOrderDto)).thenThrow(new RuntimeException("Some error"));

        // Act
        ResponseEntity<OrderDto> responseEntity = orderController.createOrder(inputOrderDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}