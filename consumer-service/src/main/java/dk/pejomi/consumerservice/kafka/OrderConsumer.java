package dk.pejomi.consumerservice.kafka;

import lombok.Getter;
import dk.pejomi.basedomain.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.concurrent.CountDownLatch;

@Service
@Getter
public class OrderConsumer {

    private final CountDownLatch latch = new CountDownLatch(1);
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);


    @KafkaListener(
            topics = "${spring.kafka.order_topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));

        // save the order event into the database
        latch.countDown();
    }

}
