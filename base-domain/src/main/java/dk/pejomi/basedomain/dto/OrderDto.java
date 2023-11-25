package dk.pejomi.basedomain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long consumerId;
    private Long restaurantId;
    private String orderState;
    private double price;
    private List<OrderItemDto> orderItems;
}

