package dk.pejomi.restaurantservice.service.impl;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.mapper.RestaurantMapper;
import dk.pejomi.restaurantservice.model.Restaurant;
import dk.pejomi.restaurantservice.repository.RestaurantRepository;
import dk.pejomi.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDTO) {

        Restaurant restaurant = restaurantRepository.save(RestaurantMapper.INSTANCE.toRestaurant(restaurantDTO));

        return RestaurantMapper.INSTANCE.toRestaurantDTO(restaurant);

    }

    @Override
    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return RestaurantMapper.INSTANCE.toRestaurantDTO(restaurant);
    }

    @Override
    public List<RestaurantDto> getRestaurantsByZipCode(String zipCode) {
        List<Restaurant> restaurants = restaurantRepository.findAllByZipCode(zipCode);
        return RestaurantMapper.INSTANCE.toRestaurantDTOs(restaurants);
    }

    @Override
    public List<RestaurantDto> getRestaurantsByCity(String city) {
        List<Restaurant> restaurants = restaurantRepository.findAllByCity(city);
        return RestaurantMapper.INSTANCE.toRestaurantDTOs(restaurants);
    }

}
