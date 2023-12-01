package dk.pejomi.restaurantservice.service.impl;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.mapper.RestaurantMapper;
import dk.pejomi.restaurantservice.model.Restaurant;
import dk.pejomi.restaurantservice.repository.RestaurantRepository;
import dk.pejomi.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDTO) {

        Restaurant restaurant = restaurantRepository.save(RestaurantMapper.INSTANCE.toRestaurant(restaurantDTO));

        return RestaurantMapper.INSTANCE.toRestaurantDTO(restaurant);

    }
}
