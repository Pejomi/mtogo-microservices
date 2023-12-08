package dk.pejomi.orderservice.event;

import dk.pejomi.orderservice.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEvent {
    private String message;
    private String status;
    private OrderDto orderDto;
}
