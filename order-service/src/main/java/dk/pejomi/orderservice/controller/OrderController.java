package dk.pejomi.orderservice.controller;

import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        try {
            return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Error occurred while creating order", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (RuntimeException e) {
            log.error("Order not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/approve/{id}")
    public ResponseEntity<OrderDto> approveOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.approveOrder(id));
        } catch (RuntimeException e) {
            log.error("Order not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/decline/{id}")
    public ResponseEntity<OrderDto> declineOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.declineOrder(id));
        } catch (RuntimeException e) {
            log.error("Order not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<OrderDto>> getAllOrdersByRestaurantId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getAllOrdersByRestaurantId(id));
        } catch (RuntimeException e) {
            log.error("Order not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/restaurant/created/{id}")
    public ResponseEntity<List<OrderDto>> getAllCreatedOrdersByRestaurantId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getAllCreatedOrdersByRestaurantId(id));
        } catch (RuntimeException e) {
            log.error("Order not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/restaurant/active/{id}")
    public ResponseEntity<List<OrderDto>> getAllActiveOrdersByRestaurantId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getAllActiveOrdersByRestaurantId(id));
        } catch (RuntimeException e) {
            log.error("Order not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/restaurant/declined/{id}")
    public ResponseEntity<List<OrderDto>> getAllDeclinedOrdersByRestaurantId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getAllDeclinedOrdersByRestaurantId(id));
        } catch (RuntimeException e) {
            log.error("Order not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

