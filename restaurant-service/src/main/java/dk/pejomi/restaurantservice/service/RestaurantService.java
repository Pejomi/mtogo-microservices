package dk.pejomi.restaurantservice.service;

import dk.pejomi.basedomain.dto.RestaurantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {

    RestaurantDto createRestaurant(RestaurantDto restaurantDTO);
    RestaurantDto getRestaurantById(Long id);
    List<RestaurantDto> getRestaurantsByZipCode(String zipCode);
    List<RestaurantDto> getRestaurantsByCity(String city);
    List<RestaurantDto> getActiveRestaurantsByZipCode(String zipCode);
    List<RestaurantDto> getActiveRestaurantsByCity(String city);
    List<RestaurantDto> getPendingRestaurants();
    RestaurantDto approveRestaurant(Long restaurantId);



}
