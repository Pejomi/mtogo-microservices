package dk.pejomi.courierservice.service.impl;

import dk.pejomi.courierservice.dto.CourierDto;
import dk.pejomi.courierservice.model.Courier;
import dk.pejomi.courierservice.repository.CourierRepository;
import dk.pejomi.courierservice.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;

    @Override
    public Flux<Courier> getAllCouriers() {
        return courierRepository.findAll()
                // Just to simulate a slow service
                .delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Mono<Courier> getCourierById(Long id) {
        return courierRepository.findById(id);
    }

    @Override
    public Mono<Courier> createCourier(CourierDto courierDto) {
        return courierRepository.save(
                Courier.builder()
                        .vehicle(courierDto.getVehicle())
                        .firstname(courierDto.getFirstname())
                        .lastname(courierDto.getLastname())
                        .country(courierDto.getCountry())
                        .zipCode(courierDto.getZipCode())
                        .phone(courierDto.getPhone())
                        .email(courierDto.getEmail())
                        .active(courierDto.isActive())
                        .build()
        );
    }
}
