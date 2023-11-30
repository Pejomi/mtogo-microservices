package dk.pejomi.consumerservice.kafka;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.consumerservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserConsumer {

    private final ConsumerService consumerService;

    @KafkaListener(topics = "create_consumer_topics", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CreateConsumerEvent event){
        System.out.printf("Message received => %s%n", event);


        consumerService.createConsumer(event.getConsumerDTO());
    }
}
