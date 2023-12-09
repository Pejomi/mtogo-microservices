package dk.pejomi.orderservice.controller;

import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        try {
            return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Error occurred while creating order", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

