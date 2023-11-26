package dk.pejomi.basedomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private String description;
    private String restaurantState;
}
