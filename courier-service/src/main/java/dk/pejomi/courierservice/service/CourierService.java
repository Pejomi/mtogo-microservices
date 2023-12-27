package dk.pejomi.courierservice.service;

import dk.pejomi.courierservice.dto.CourierDto;
import dk.pejomi.courierservice.model.Courier;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourierService {

    Flux<Courier> getAllCouriers();
    Mono<Courier> getCourierById(Long id);
    Mono<Courier> createCourier(CourierDto courierDto);
}
