package dk.pejomi.restaurantservice.repository;

import dk.pejomi.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByZipCode(String zipCode);
    List<Restaurant> findAllByCity(String city);
    List<Restaurant> findAllByRestaurantStateAndZipCode(String restaurantState, String zipCode);
    List<Restaurant> findAllByRestaurantStateAndCity(String restaurantState, String city);
    List<Restaurant> findAllByRestaurantState(String restaurantState);


}
