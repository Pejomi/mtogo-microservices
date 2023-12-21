package dk.pejomi.orderservice.service;
import dk.pejomi.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto) ;
    OrderDto getOrderById(Long id);
    OrderDto approveOrder(Long id);
    OrderDto declineOrder(Long id);
    List<OrderDto> getAllOrdersByRestaurantId(Long id);
    List<OrderDto> getAllCreatedOrdersByRestaurantId(Long id);
    List<OrderDto> getAllActiveOrdersByRestaurantId(Long id);
    List<OrderDto> getAllDeclinedOrdersByRestaurantId(Long id);



}
