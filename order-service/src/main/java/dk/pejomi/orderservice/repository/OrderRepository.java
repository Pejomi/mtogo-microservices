package dk.pejomi.orderservice.repository;

import dk.pejomi.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByRestaurantId(Long restaurantId);
    List<Order> findAllByRestaurantIdAndOrderState(Long restaurantId, String orderState);


}
