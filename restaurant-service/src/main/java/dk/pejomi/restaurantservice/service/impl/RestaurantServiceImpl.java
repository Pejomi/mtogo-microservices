package dk.pejomi.restaurantservice.service.impl;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.kafka.ApprovalProducer;
import dk.pejomi.restaurantservice.mapper.RestaurantMapper;
import dk.pejomi.restaurantservice.model.Restaurant;
import dk.pejomi.restaurantservice.repository.RestaurantRepository;
import dk.pejomi.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ApprovalProducer approvalProducer;

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDTO) {

        // New restaurant have PENDING status
        restaurantDTO.setRestaurantState("PENDING");

        Restaurant restaurant = restaurantRepository.save(RestaurantMapper.INSTANCE.toRestaurant(restaurantDTO));

        // publish message to admin-service
        approvalProducer.sendMessage("New restaurant application: " + restaurant.getId());

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
