package dk.pejomi.restaurantservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = RestaurantController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class RestaurantControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @Autowired
    private ObjectMapper objectMapper;

    private RestaurantDto restaurantDto;
    private List<RestaurantDto> restaurantDtoList;

    @BeforeEach
    public void init() {
        restaurantDto = RestaurantDto.builder()
                .name("Restaurant")
                .street("Street 1")
                .city("Copenhagen")
                .zipCode("1234")
                .phone("12345678")
                .country("Denmark")
                .homepage("www.restaurant.com")
                .restaurantState("PENDING")
                .build();

        RestaurantDto restaurantDto2 = RestaurantDto.builder()
                .name("Restaurant2")
                .street("Street 2")
                .city("Copenhagen")
                .zipCode("1234")
                .phone("12345678")
                .country("Denmark")
                .homepage("www.restaurant2.com")
                .restaurantState("PENDING")
                .build();

        restaurantDtoList = List.of(restaurantDto, restaurantDto2);
    }

    @Test
    void should_return_restaurant_when_getRestaurantById() throws Exception {
        // Arrange
        when(restaurantService.getRestaurantById(1L)).thenReturn(restaurantDto);

        // Act
        ResultActions response = mockMvc.perform(get("/api/restaurant/1"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(result -> assertEquals(restaurantDto, objectMapper.readValue(result.getResponse().getContentAsString(), RestaurantDto.class)));
    }

    @Test
    void should_catch_exception_when_getRestaurantById() throws Exception {
        // Arrange
        when(restaurantService.getRestaurantById(any(Long.class))).thenThrow(new RuntimeException("Restaurant not found"));

        // Act
        ResultActions response = mockMvc.perform(get("/api/restaurant/2"));

        // Assert
        response.andExpect(status().isNotFound());
    }

    @Test
    void should_return_restaurant_when_getRestaurantsByZipCode() throws Exception {
        // Arrange
        when(restaurantService.getRestaurantsByZipCode("1234")).thenReturn(restaurantDtoList);

        // Act
        ResultActions response = mockMvc.perform(get("/api/restaurant/zip/1234"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(result -> assertEquals(restaurantDtoList.size(), objectMapper.readValue(result.getResponse().getContentAsString(), List.class).size()));
    }

    @Test
    void should_return_restaurant_when_getRestaurantsByCity() throws Exception {
        // Arrange
        when(restaurantService.getRestaurantsByCity("Copenhagen")).thenReturn(restaurantDtoList);

        // Act
        ResultActions response = mockMvc.perform(get("/api/restaurant/city/Copenhagen"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(result -> assertEquals(restaurantDtoList.size(), objectMapper.readValue(result.getResponse().getContentAsString(), List.class).size()));
    }

}