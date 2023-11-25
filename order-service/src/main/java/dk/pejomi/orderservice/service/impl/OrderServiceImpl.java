package dk.pejomi.orderservice.service.impl;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.OrderEvent;
import dk.pejomi.basedomain.dto.OrderItemDto;
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
        Order order = orderRepository.save(
//                OrderMapper.INSTANCE.toOrder(orderDto));
                mapOrderDtoToOrder(orderDto));

        // Kafka event
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrderDto(orderDto);
        orderProducer.sendMessage(orderEvent);

        return OrderMapper.INSTANCE.toOrderDTO(order);
    }

    private Order mapOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setConsumerId(orderDto.getConsumerId());
        order.setRestaurantId(orderDto.getRestaurantId());
        order.setOrderState(orderDto.getOrderState());
        order.setPrice(orderDto.getPrice());

        if (orderDto.getOrderItems() != null) {
            List<OrderItem> orderItems = orderDto.getOrderItems().stream()
                    .map(this::mapOrderItemDtoToOrderItem)
                    .toList();

            orderItems.forEach(orderItem -> orderItem.setOrder(order));

            order.setOrderItems(orderItems);
        }


        return order;
    }

    private OrderItem mapOrderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setMenuItemId(orderItemDto.getMenuItemId());
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());

        return orderItem;
    }
}
