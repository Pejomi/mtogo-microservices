package dk.pejomi.orderservice.mapper;

import dk.pejomi.orderservice.dto.OrderDto;
import dk.pejomi.orderservice.dto.OrderItemDto;
import dk.pejomi.orderservice.model.Order;
import dk.pejomi.orderservice.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderItems", target = "orderItemsDto")
    OrderDto orderToOrderDto(Order order);


    @Mapping(source = "orderItemsDto", target = "orderItems")
    Order orderDtoToOrder(OrderDto orderDto);

    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);

    //@Mapping(target = "order", ignore = true)
    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);

    List<OrderDto> ordersToOrderDtos(List<Order> orders);


}
