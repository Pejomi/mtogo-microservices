package dk.pejomi.authservice.service;

import dk.pejomi.authservice.model.RegisterConsumerDto;
import dk.pejomi.authservice.model.RegisterRestaurantDto;

public interface RegisterService {

    String registerConsumer(RegisterConsumerDto registerConsumerDto);
    String registerRestaurant(RegisterRestaurantDto registerRestaurantDto);
    Boolean isEmailAvailable(String email);
}
