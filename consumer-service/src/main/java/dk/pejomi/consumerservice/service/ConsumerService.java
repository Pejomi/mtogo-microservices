package dk.pejomi.consumerservice.service;

import dk.pejomi.basedomain.dto.ConsumerDto;

public interface ConsumerService {
    ConsumerDto createConsumer(ConsumerDto consumerDTO);
}
