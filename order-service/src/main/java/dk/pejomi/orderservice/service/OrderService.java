package dk.pejomi.orderservice.service;


import dk.pejomi.basedomain.dto.OrderDto;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

}
