package dk.pejomi.consumerservice.service.impl;

import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.consumerservice.mapper.ConsumerMapper;
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
    public ConsumerDto createConsumer(ConsumerDto consumerDTO) {
        Consumer consumer = consumerRepository.save(
                ConsumerMapper.INSTANCE.toConsumer(consumerDTO));
        return ConsumerMapper.INSTANCE.toConsumerDTO(consumer);
    }
}
