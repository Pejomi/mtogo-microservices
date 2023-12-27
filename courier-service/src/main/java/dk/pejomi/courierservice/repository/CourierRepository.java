package dk.pejomi.courierservice.repository;

import dk.pejomi.courierservice.model.Courier;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CourierRepository extends ReactiveCrudRepository<Courier, Long> {
}
