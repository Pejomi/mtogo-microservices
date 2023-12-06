package dk.pejomi.consumerservice.integration;

import dk.pejomi.basedomain.event.OrderEvent;
import dk.pejomi.consumerservice.ConsumerServiceApplication;
import dk.pejomi.orderservice.OrderServiceApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import dk.pejomi.consumerservice.kafka.OrderConsumer;
import dk.pejomi.orderservice.kafka.OrderProducer;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {OrderServiceApplication.class, ConsumerServiceApplication.class})
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@ComponentScan(basePackages = "dk.pejomi.orderservice")
@Disabled
public class KafkaIntegrationTest {

    @Autowired
    private OrderConsumer consumer;

    @Autowired
    private OrderProducer producer;

    @Test
    public void should_receive_message_when_send_message() throws Exception {
        // arrange
        String message = "Hello World";
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage(message);

        // act
        producer.sendMessage(orderEvent);

        // assert
        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
    }
}
