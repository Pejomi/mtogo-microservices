package dk.pejomi.basedomain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String consumerId;
    private String restaurantId;
    private String orderState;
    private double price;
    private List<OrderItemDto> orderItems;
}

