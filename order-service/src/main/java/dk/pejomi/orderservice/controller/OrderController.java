package dk.pejomi.orderservice.controller;

import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {

        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }
}

