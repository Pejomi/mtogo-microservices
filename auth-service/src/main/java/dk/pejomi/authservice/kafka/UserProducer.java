package dk.pejomi.authservice.kafka;

import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserProducer {


    @Value("${spring.kafka.consumer_topic.name}")
    private String consumerTopicName;

    @Value("${spring.kafka.restaurant_topic.name}")
    private String restaurantTopicName;

    private final KafkaTemplate<String, CreateConsumerEvent> consumerKafkaTemplate;
    private final KafkaTemplate<String, CreateRestaurantEvent> restaurantKafkaTemplate;

    public UserProducer(
            KafkaTemplate<String, CreateConsumerEvent> consumerKafkaTemplate,
            KafkaTemplate<String, CreateRestaurantEvent> restaurantKafkaTemplate)
    {
        this.consumerKafkaTemplate = consumerKafkaTemplate;
        this.restaurantKafkaTemplate = restaurantKafkaTemplate;
    }

    public CreateConsumerEvent sendCreateConsumer(CreateConsumerEvent event){
        log.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<CreateConsumerEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, consumerTopicName)
                .build();
        consumerKafkaTemplate.send(message);

        return event;
    }

    public CreateRestaurantEvent sendCreateRestaurant(CreateRestaurantEvent event){
        log.info(String.format("Order event => %s", event.toString()));

        // create Message
        Message<CreateRestaurantEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, restaurantTopicName)
                .build();
        restaurantKafkaTemplate.send(message);

        return event;
    }
}
