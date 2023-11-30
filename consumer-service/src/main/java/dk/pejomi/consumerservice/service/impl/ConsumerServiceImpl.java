package dk.pejomi.consumerservice.service.impl;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.consumerservice.dto.ConsumerDTO;
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
    public ConsumerDTO createConsumer(ConsumerDTO consumerDTO) {
        Consumer consumer = consumerRepository.save(
                ConsumerMapper.INSTANCE.toConsumer(consumerDTO));
        return ConsumerMapper.INSTANCE.toConsumerDTO(consumer);
    }

}
