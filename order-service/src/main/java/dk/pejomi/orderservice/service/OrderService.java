package dk.pejomi.orderservice.service;
import dk.pejomi.orderservice.dto.OrderDto;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto) ;
}
