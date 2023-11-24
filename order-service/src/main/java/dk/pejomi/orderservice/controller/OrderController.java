package dk.pejomi.orderservice.controller;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.OrderEvent;
import dk.pejomi.basedomain.dto.OrderItemDto;
import dk.pejomi.orderservice.kafka.OrderProducer;
import dk.pejomi.orderservice.service.OrderService;
import dk.pejomi.orderservice.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto){

        orderService.createOrder(orderDto);

        return "Order placed successfully ...";
    }
}
