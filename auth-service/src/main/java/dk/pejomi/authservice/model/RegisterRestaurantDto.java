package dk.pejomi.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRestaurantDto {


        private String email;
        private String password;
        private String name;
        private String phone;
        private String street;
        private String city;
        private String zipCode;
        private String country;
        private String homepage;
        private String restaurantState;
}
