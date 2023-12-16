package dk.pejomi.restaurantservice.controller;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @InjectMocks
    private RestaurantController restaurantController;

    @Mock
    private RestaurantService restaurantService;

    @Test
    public void should_return_restaurant_when_getRestaurantById() {
        // Arrange
        Long restaurantId = 1L;
        RestaurantDto restaurantDto = new RestaurantDto(); // Create a sample RestaurantDto
        when(restaurantService.getRestaurantById(any(Long.class))).thenReturn(restaurantDto);

        // Act
        ResponseEntity<RestaurantDto> response = restaurantController.getRestaurantById(restaurantId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantDto, response.getBody());
    }

    @Test
    public void should_return_not_found_when_getRestaurantById_throws_exception() {
        // Arrange
        Long restaurantId = 1L;
        when(restaurantService.getRestaurantById(any(Long.class))).thenThrow(new RuntimeException("Not found"));

        // Act
        ResponseEntity<RestaurantDto> response = restaurantController.getRestaurantById(restaurantId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void should_return_restaurants_when_getRestaurantsByZipCode() {
        // Arrange
        String zipCode = "2800";
        List<RestaurantDto> restaurantList = Arrays.asList(new RestaurantDto(), new RestaurantDto());
        when(restaurantService.getRestaurantsByZipCode(any(String.class))).thenReturn(restaurantList);

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getRestaurantsByZipCode(zipCode);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantList, response.getBody());
    }

    @Test
    public void should_return_not_found_when_getRestaurantsByZipCode_throws_exception() {
        // Arrange
        String zipCode = "2800";
        when(restaurantService.getRestaurantsByZipCode(any(String.class))).thenReturn(List.of());

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getRestaurantsByZipCode(zipCode);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    public void should_return_restaurants_when_getRestaurantsByCity() {
        // Arrange
        String city = "Lyngby";
        List<RestaurantDto> restaurantList = Arrays.asList(new RestaurantDto(), new RestaurantDto());
        when(restaurantService.getRestaurantsByCity(any(String.class))).thenReturn(restaurantList);

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getRestaurantsByCity(city);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantList, response.getBody());
    }

    @Test
    public void should_return_not_found_when_getRestaurantsByCity_throws_exception() {
        // Arrange
        String city = "Lyngby";
        when(restaurantService.getRestaurantsByCity(any(String.class))).thenReturn(List.of());

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getRestaurantsByCity(city);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void should_return_active_restaurants_when_getActiveRestaurantsByZipCode() {
        // Arrange
        String zipCode = "2800";
        List<RestaurantDto> restaurantList = Arrays.asList(new RestaurantDto(), new RestaurantDto());
        when(restaurantService.getActiveRestaurantsByZipCode(any(String.class))).thenReturn(restaurantList);

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getActiveRestaurantsByZipCode(zipCode);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantList, response.getBody());
    }

    @Test
    public void should_return_not_found_when_getActiveRestaurantsByZipCode_throws_exception() {
        // Arrange
        String zipCode = "2800";
        when(restaurantService.getActiveRestaurantsByZipCode(any(String.class))).thenReturn(List.of());

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getActiveRestaurantsByZipCode(zipCode);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void should_return_active_restaurants_when_getActiveRestaurantsByCity() {
        // Arrange
        String city = "Lyngby";
        List<RestaurantDto> restaurantList = Arrays.asList(new RestaurantDto(), new RestaurantDto());
        when(restaurantService.getActiveRestaurantsByCity(any(String.class))).thenReturn(restaurantList);

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getActiveRestaurantsByCity(city);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantList, response.getBody());
    }

    @Test
    void should_return_not_found_when_getActiveRestaurantsByCity_throws_exception() {
        // Arrange
        String city = "Lyngby";
        when(restaurantService.getActiveRestaurantsByCity(any(String.class))).thenReturn(List.of());

        // Act
        ResponseEntity<List<RestaurantDto>> response = restaurantController.getActiveRestaurantsByCity(city);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void should_return_approved_restaurant_when_approveRestaurant() {
        // Arrange
        Long restaurantId = 1L;
        RestaurantDto restaurantDto = new RestaurantDto(); // Create a sample RestaurantDto
        when(restaurantService.approveRestaurant(any(Long.class))).thenReturn(restaurantDto);

        // Act
        ResponseEntity<RestaurantDto> response = restaurantController.approveRestaurant(restaurantId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantDto, response.getBody());
    }

    @Test
    public void should_return_not_found_when_approveRestaurant_throws_exception() {
        // Arrange
        Long restaurantId = 1L;
        when(restaurantService.approveRestaurant(any(Long.class))).thenThrow(new RuntimeException("Not found"));

        // Act
        ResponseEntity<RestaurantDto> response = restaurantController.approveRestaurant(restaurantId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}