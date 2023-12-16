package dk.pejomi.restaurantservice.repository;

import dk.pejomi.restaurantservice.model.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RestaurantRepositoryIT {

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Test
    void should_find_restaurant_by_id() {
        // Arrange
        Restaurant restaurant = restaurantRepository.findAll().get(0);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findById(restaurant.getId()).orElse(null);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }

    @Test
    void should_find_restaurant_by_zip_code() {
        // Arrange
        Restaurant restaurant = restaurantRepository.findAll().get(0);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findAllByZipCode(restaurant.getZipCode()).get(0);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }

    @Test
    void should_find_restaurant_by_city() {
        // Arrange
        Restaurant restaurant = restaurantRepository.findAll().get(0);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findAllByCity(restaurant.getCity()).get(0);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }

    @Test
    void should_find_restaurant_by_restaurant_state_and_zip_code() {
        // Arrange
        Restaurant restaurant = restaurantRepository.findAll().get(0);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findAllByRestaurantStateAndZipCode(restaurant.getRestaurantState(), restaurant.getZipCode()).get(0);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }

    @Test
    void should_find_restaurant_by_restaurant_state_and_city() {
        // Arrange
        Restaurant restaurant = restaurantRepository.findAll().get(0);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findAllByRestaurantStateAndCity(restaurant.getRestaurantState(), restaurant.getCity()).get(0);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }

    @Test
    void should_find_restaurant_by_restaurant_state() {
        // Act
        Restaurant foundRestaurant = restaurantRepository.findAllByRestaurantState("PENDING").get(0);

        // Assert
        assertNotNull(foundRestaurant);
    }


    @Test
    @Transactional
    void should_approve_restaurant() throws InterruptedException {
        // Arrange
        Restaurant restaurant = restaurantRepository.findAllByRestaurantState("PENDING").get(0);
        restaurant.setRestaurantState("APPROVED");

        // Act
        Restaurant approvedRestaurant = restaurantRepository.save(restaurant);

        // Assert
        assertEquals(approvedRestaurant.getRestaurantState(), "APPROVED");
    }

}