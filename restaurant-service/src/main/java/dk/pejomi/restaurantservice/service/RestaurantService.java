package dk.pejomi.restaurantservice.service;

import dk.pejomi.basedomain.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    RestaurantDto createRestaurant(RestaurantDto restaurantDTO);
    RestaurantDto getRestaurantById(Long id);
    List<RestaurantDto> getRestaurantsByZipCode(String zipCode);
    List<RestaurantDto> getRestaurantsByCity(String city);



}
