package dk.pejomi.orderservice.service.impl;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.event.OrderEvent;
import dk.pejomi.basedomain.dto.OrderItemDto;
import dk.pejomi.orderservice.kafka.OrderProducer;
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

        //return OrderMapper.INSTANCE.toOrderDTO(order);
        return mapOrderToOrderDto(order);
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

    private OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setConsumerId(order.getConsumerId());
        orderDto.setRestaurantId(order.getRestaurantId());
        orderDto.setOrderState(order.getOrderState());
        orderDto.setPrice(order.getPrice());

        if (order.getOrderItems() != null) {
            List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                    .map(this::mapOrderItemToOrderItemDto)
                    .toList();

            orderDto.setOrderItems(orderItemDtos);
        }

        return orderDto;
    }

    private OrderItemDto mapOrderItemToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setMenuItemId(orderItem.getMenuItemId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());

        return orderItemDto;
    }
}
