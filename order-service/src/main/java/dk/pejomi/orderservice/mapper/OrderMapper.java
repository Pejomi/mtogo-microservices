package dk.pejomi.orderservice.mapper;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper( OrderMapper.class );

//    @Mapping(target = "orderItems", ignore = true)
    Order toOrder(OrderDto orderDto);


    OrderDto toOrderDTO(Order order);
}
