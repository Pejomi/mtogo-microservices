package dk.pejomi.restaurantservice.repository;

import dk.pejomi.restaurantservice.model.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;


    private static Restaurant restaurant;

    @BeforeAll
    static void beforeAll() {
        restaurant = Restaurant.builder()
                .name("Restaurant")
                .street("Street 1")
                .city("Copenhagen")
                .zipCode("1234")
                .phone("12345678")
                .country("Denmark")
                .homepage("www.restaurant.com")
                .restaurantState("PENDING")
                .build();


    }

    @AfterEach
    void tearDown() {
        restaurantRepository.deleteAll();
    }

    @Test
    void should_find_restaurant_by_id() {
        // Arrange
        restaurantRepository.save(restaurant);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findById(restaurant.getId()).orElse(null);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }

    @Test
    void should_find_restaurant_by_zip_code() {
        // Arrange
        restaurantRepository.save(restaurant);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findAllByZipCode(restaurant.getZipCode()).get(0);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }

    @Test
    void should_find_restaurant_by_city() {
        // Arrange
        restaurantRepository.save(restaurant);

        // Act
        Restaurant foundRestaurant = restaurantRepository.findAllByCity(restaurant.getCity()).get(0);

        // Assert
        assertNotNull(foundRestaurant);
        assertEquals(foundRestaurant.getName(), restaurant.getName());
    }


}