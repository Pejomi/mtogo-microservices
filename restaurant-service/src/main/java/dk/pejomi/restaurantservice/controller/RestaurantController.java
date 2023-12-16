package dk.pejomi.restaurantservice.controller;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController implements RestaurantApi {

    private final RestaurantService restaurantService;


    @Override
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restaurantService.getRestaurantById(id));
        } catch (RuntimeException e) {
            log.error("Restaurant not found with id [{}]", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @GetMapping("/zip/{zipCode}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByZipCode(@PathVariable String zipCode) {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsByZipCode(zipCode);
        if (restaurants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(restaurantService.getRestaurantsByZipCode(zipCode));
    }

    @Override
    @GetMapping("/city/{city}")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByCity(@PathVariable String city) {
        List<RestaurantDto> restaurants = restaurantService.getRestaurantsByCity(city);
        if (restaurants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(restaurantService.getRestaurantsByCity(city));
    }

    @Override
    @GetMapping("/active/zip/{zipCode}")
    public ResponseEntity<List<RestaurantDto>> getActiveRestaurantsByZipCode(@PathVariable String zipCode) {
        List<RestaurantDto> restaurants = restaurantService.getActiveRestaurantsByZipCode(zipCode);
        if (restaurants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(restaurantService.getActiveRestaurantsByZipCode(zipCode));
    }

    @Override
    @GetMapping("/active/city/{city}")
    public ResponseEntity<List<RestaurantDto>> getActiveRestaurantsByCity(@PathVariable String city) {
        List<RestaurantDto> restaurants = restaurantService.getActiveRestaurantsByCity(city);
        if (restaurants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(restaurantService.getActiveRestaurantsByCity(city));
    }

    @PutMapping("/approve/{restaurantId}")
    public ResponseEntity<RestaurantDto> approveRestaurant(@PathVariable Long restaurantId) {
        try {
            return ResponseEntity.ok(restaurantService.approveRestaurant(restaurantId));
        } catch (RuntimeException e) {
            log.error("Restaurant not found with id [{}]", restaurantId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
