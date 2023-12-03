package dk.pejomi.restaurantservice.repository;

import dk.pejomi.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByZipCode(String zipCode);
    List<Restaurant> findAllByCity(String city);
}
