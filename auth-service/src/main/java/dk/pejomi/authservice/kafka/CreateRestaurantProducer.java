package dk.pejomi.authservice.kafka;

import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateRestaurantProducer {

    private final NewTopic topicRestaurant;

    private final KafkaTemplate<String, CreateRestaurantEvent> kafkaTemplate;

    public CreateRestaurantProducer(NewTopic topicRestaurant, KafkaTemplate<String, CreateRestaurantEvent> kafkaTemplate) {
        this.topicRestaurant = topicRestaurant;
        this.kafkaTemplate = kafkaTemplate;
    }
    public CreateRestaurantEvent sendCreateRestaurant(CreateRestaurantEvent event){
        log.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<CreateRestaurantEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topicRestaurant.name())
                .build();
        kafkaTemplate.send(message);

        return event;
    }
}
