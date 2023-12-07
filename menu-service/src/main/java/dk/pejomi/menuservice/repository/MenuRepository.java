package dk.pejomi.menuservice.repository;

import dk.pejomi.menuservice.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findAllByRestaurantId(Long restaurantId);
}
