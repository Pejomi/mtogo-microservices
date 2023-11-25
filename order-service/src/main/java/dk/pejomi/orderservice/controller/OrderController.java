package dk.pejomi.orderservice.controller;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {

        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }
}

