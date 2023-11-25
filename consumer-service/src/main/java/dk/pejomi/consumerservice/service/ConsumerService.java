package dk.pejomi.consumerservice.service;

import dk.pejomi.basedomain.dto.ConsumerDTO;

public interface ConsumerService {
    ConsumerDTO createConsumer(ConsumerDTO consumerDTO);
}
