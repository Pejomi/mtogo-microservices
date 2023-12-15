package dk.pejomi.authservice.kafka;

import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserProducerTest {

    @Mock
    private KafkaTemplate<String, CreateConsumerEvent> kafkaTemplate;

    @InjectMocks
    private UserProducer userProducer;

    @Test
    void sendCreateConsumer_ShouldSendMessageToKafka() {
        // Given
        CreateConsumerEvent event = new CreateConsumerEvent(/* your event initialization */);

        // When
        userProducer.sendCreateConsumer(event);

        // Then
        verify(kafkaTemplate).send(any(Message.class));
    }

    @Test
    void sendCreateRestaurant_ShouldSendMessageToKafka() {
        // Given
        CreateRestaurantEvent event = new CreateRestaurantEvent(/* your event initialization */);

        // When
        userProducer.sendCreateRestaurant(event);

        // Then
        verify(kafkaTemplate).send(any(Message.class));
    }
}