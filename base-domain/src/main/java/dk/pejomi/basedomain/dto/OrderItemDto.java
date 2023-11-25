package dk.pejomi.basedomain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Long menuItemId;
    private double price;
    private int quantity;
    @ToString.Exclude
    private OrderDto order;


}
