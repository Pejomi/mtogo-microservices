package dk.pejomi.authservice.kafka;

import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, CreateConsumerEvent> kafkaTemplate;

    public UserProducer(NewTopic topic, KafkaTemplate<String, CreateConsumerEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public CreateConsumerEvent sendCreateConsumer(CreateConsumerEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<CreateConsumerEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);

        return event;
    }

    public CreateRestaurantEvent sendCreateRestaurant(CreateRestaurantEvent event){
        LOGGER.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<CreateRestaurantEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);

        return event;
    }
}
