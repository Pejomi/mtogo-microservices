package dk.pejomi.consumerservice.service.impl;

import dk.pejomi.consumerservice.dto.ConsumerDTO;
import dk.pejomi.consumerservice.model.Consumer;
import dk.pejomi.consumerservice.repository.ConsumerRepository;
import dk.pejomi.consumerservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;
    @Override
    public ConsumerDTO createConsumer(ConsumerDTO consumerDTO) {
        Consumer consumer = consumerRepository.save(
                Consumer.builder()
                        .username(consumerDTO.getUsername())
                        .email(consumerDTO.getEmail())
                        .phone(consumerDTO.getPhone())
                        .street(consumerDTO.getStreet())
                        .city(consumerDTO.getCity())
                        .zipCode(consumerDTO.getZipCode())
                        .country(consumerDTO.getCountry())
                        .build()
        );
        return mapToConsumerDTO(consumer);
    }

    private ConsumerDTO mapToConsumerDTO(Consumer consumer) {
        return ConsumerDTO.builder()
                .username(consumer.getUsername())
                .email(consumer.getEmail())
                .phone(consumer.getPhone())
                .street(consumer.getStreet())
                .city(consumer.getCity())
                .zipCode(consumer.getZipCode())
                .country(consumer.getCountry())
                .build();
    }
}
