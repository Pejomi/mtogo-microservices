package dk.pejomi.restaurantservice.kafka;

import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import dk.pejomi.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final RestaurantService restaurantService;

    @KafkaListener(
            topics = "create_restaurant_topics",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CreateRestaurantEvent event) {
        log.info(String.format("Message received => %s%n", event.toString()));

        restaurantService.createRestaurant(event.getRestaurantDto());
    }
}
