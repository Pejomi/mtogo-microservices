package dk.pejomi.restaurantservice.service.impl;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.model.Restaurant;
import dk.pejomi.restaurantservice.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private Restaurant restaurant;

    private RestaurantDto restaurantDTO;

    @BeforeEach
    void setUp() {
        restaurantDTO = RestaurantDto.builder()
                .id(1L)
                .phone("12345678")
                .city("Copenhagen")
                .country("Denmark")
                .street("Street 1")
                .zipCode("1234")
                .build();

        restaurant = Restaurant.builder()
                .id(1L)
                .phone("12345678")
                .city("Copenhagen")
                .country("Denmark")
                .street("Street 1")
                .zipCode("1234")
                .build();
    }

    @Test
    void should_return_restaurant_when_creating_restaurant() {
        //Arrange
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        //Act
        RestaurantDto actual = restaurantService.createRestaurant(restaurantDTO);

        //Assert
        assertEquals(restaurantDTO.toString(), actual.toString());
    }

}