package dk.pejomi.consumerservice.kafka;

import dk.pejomi.basedomain.event.OrderEvent;
import dk.pejomi.consumerservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {


    private final ConsumerService consumerService;

    @KafkaListener(
            topics = "${spring.kafka.order_topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event) {
        log.info(String.format("Order event received in stock service => %s", event.toString()));

        consumerService.increaseOrderCount(event.getOrderDto().getConsumerId());
    }

}
