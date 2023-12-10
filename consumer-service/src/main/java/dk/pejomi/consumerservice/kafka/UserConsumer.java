package dk.pejomi.consumerservice.kafka;

import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.consumerservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final ConsumerService consumerService;

    @KafkaListener(
            topics = "create_consumer_topics",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CreateConsumerEvent event) {
        log.info(String.format("Message received => %s%n", event.toString()));

        consumerService.createConsumer(event.getConsumerDTO());
    }
}
