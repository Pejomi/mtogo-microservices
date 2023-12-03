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

import java.util.List;
import java.util.Optional;

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
    private Restaurant restaurant2;
    private List<Restaurant> restaurants;

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

        restaurant2 = Restaurant.builder()
                .id(2L)
                .phone("12345678")
                .city("Copenhagen")
                .country("Denmark")
                .street("Street 1")
                .zipCode("1234")
                .build();

        restaurants = List.of(restaurant, restaurant2);
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

    // getRestaurantById

    @Test
    void should_return_restaurant_when_getting_restaurant_by_id() {
        //Arrange
        when(restaurantRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(restaurant));

        //Act
        RestaurantDto actual = restaurantService.getRestaurantById(1L);

        //Assert
        assertEquals(restaurantDTO.toString(), actual.toString());
    }

    @Test
    void should_throw_exception_when_getting_restaurant_by_id() {
        //Arrange
        when(restaurantRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        //Act
        Exception exception = assertThrows(RuntimeException.class, () -> {
            restaurantService.getRestaurantById(1L);
        });

        //Assert
        assertEquals("Restaurant not found", exception.getMessage());
    }


    // getRestaurantsByZipCode
    @Test
    void should_return_restaurant_when_getting_restaurant_by_zip_code() {
        //Arrange
        when(restaurantRepository.findAllByZipCode(any(String.class))).thenReturn(restaurants);
        //Act
        List<RestaurantDto> actual = restaurantService.getRestaurantsByZipCode("1234");

        //Assert
        assertEquals(2, actual.size());
    }

    @Test
    void should_throw_exception_when_getting_restaurant_by_zip_code() {
        //Arrange
        when(restaurantRepository.findAllByZipCode(any(String.class))).thenReturn(List.of());

        //Act

        List<RestaurantDto> actual = restaurantService.getRestaurantsByZipCode("1234");


        //Assert
        assertEquals(actual.size(), 0);
    }

    // getRestaurantsByCity
    @Test
    void should_return_restaurant_when_getting_restaurant_by_city() {
        //Arrange
        when(restaurantRepository.findAllByCity(any(String.class))).thenReturn(restaurants);
        //Act
        List<RestaurantDto> actual = restaurantService.getRestaurantsByCity("Copenhagen");

        //Assert
        assertEquals(2, actual.size());
    }

    @Test
    void should_throw_exception_when_getting_restaurant_by_city() {
        //Arrange
        when(restaurantRepository.findAllByCity(any(String.class))).thenReturn(List.of());

        //Act
        List<RestaurantDto> actual = restaurantService.getRestaurantsByCity("Copenhagen");

        //Assert
        assertEquals(actual.size(), 0);
    }
}