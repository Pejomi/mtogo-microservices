package dk.pejomi.orderservice.service.impl;

import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.event.OrderEvent;
import dk.pejomi.orderservice.dto.OrderItemDto;
import dk.pejomi.orderservice.kafka.OrderProducer;
import dk.pejomi.orderservice.mapper.OrderMapper;
import dk.pejomi.orderservice.model.Order;
import dk.pejomi.orderservice.model.OrderItem;
import dk.pejomi.orderservice.repository.OrderRepository;
import dk.pejomi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderProducer orderProducer;

    private final OrderRepository orderRepository;


    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setOrderState("CREATED");
        calculateTotalPrice(orderDto);

        if (!checkTotalPriceMinimum(orderDto)) {
            orderDto.setOrderState("REJECTED");
            throw new RuntimeException("Order price is below minimum");
        }
        // Persist order items
        Order order = orderRepository.save(OrderMapper.INSTANCE.orderDtoToOrder(orderDto));

        // TODO: Start a saga here to handle the order flow: payment, delivery, etc.

        // Kafka event
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrderDto(orderDto);
        orderProducer.sendMessage(orderEvent);

        return OrderMapper.INSTANCE.orderToOrderDto(order);
    }

    private boolean checkTotalPriceMinimum(OrderDto orderDto) {
        return orderDto.getPrice() >= 100;
    }

    private void calculateTotalPrice(OrderDto orderDto) {
        orderDto.setPrice(orderDto.getOrderItemsDto().stream()
                .mapToDouble(orderItemDto -> orderItemDto.getPrice() * orderItemDto.getQuantity())
                .sum());
    }
}
