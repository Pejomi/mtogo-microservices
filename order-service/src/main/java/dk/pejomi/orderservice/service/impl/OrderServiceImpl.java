package dk.pejomi.orderservice.service.impl;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.OrderEvent;
import dk.pejomi.orderservice.kafka.OrderProducer;
import dk.pejomi.orderservice.mapper.OrderMapper;
import dk.pejomi.orderservice.model.Order;
import dk.pejomi.orderservice.repository.OrderRepository;
import dk.pejomi.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderProducer orderProducer;

    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderRepository.save(
                OrderMapper.INSTANCE.toOrder(orderDto));

        // Kafka event
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrderDto(orderDto);
        orderProducer.sendMessage(orderEvent);

        return OrderMapper.INSTANCE.toOrderDTO(order);
    }
}
