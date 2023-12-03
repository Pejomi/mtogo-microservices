package dk.pejomi.restaurantservice.controller;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController implements RestaurantApi{

    private final RestaurantService restaurantService;


    @Override
    @GetMapping("/{id}")
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
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByZipCode(@PathVariable String zipCode) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByZipCode(zipCode));
    }

    @Override
    @GetMapping("/city/{city}")
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByCity(@PathVariable String city) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByCity(city));
    }
}
