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
    private Long userId;
    private String name;
    private String street;
    private String city;
    private String zipCode;
    private String phone;
    private String country;
    private String homepage;
    private String restaurantState;
}
