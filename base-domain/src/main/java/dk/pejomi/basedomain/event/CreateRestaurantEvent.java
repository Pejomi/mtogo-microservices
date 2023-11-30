package dk.pejomi.basedomain.event;

import dk.pejomi.basedomain.dto.RestaurantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRestaurantEvent {

    private String message;
    private String status;
    private RestaurantDto restaurantDto;
}
