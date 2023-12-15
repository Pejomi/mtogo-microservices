package dk.pejomi.restaurantservice.pact;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.Message;
import au.com.dius.pact.core.model.messaging.MessagePact;
import com.fasterxml.jackson.core.type.TypeReference;
import dk.pejomi.restaurantservice.model.Restaurant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "auth-service", providerType = ProviderType.ASYNCH)
@Disabled
public class UserConsumerTest {

    @Pact(consumer = "restaurant-service")
    public MessagePact validRestaurantEventFromKafkaProvider(MessagePactBuilder builder) {
        return builder
                .expectsToReceive("valid restaurantEvent from kafka provider")
                .withContent(LambdaDsl.newJsonBody(lambdaDslJsonBody -> {
                    lambdaDslJsonBody
                            .stringType("message", "Restaurant created")
                            .object("restaurantDto", lambdaDslJsonBody1 -> {
                                lambdaDslJsonBody1
                                        .stringType("id", "1")
                                        .stringType("userId", "1")
                                        .stringType("name", "Restaurant")
                                        .stringType("street", "Gade")
                                        .stringType("city", "Valby")
                                        .stringType("zipCode", "2500")
                                        .stringType("phone", "12345678")
                                        .stringType("country", "Denmark")
                                        .stringType("homepage", "www.homepage.com")
                                        .stringType("restaurantState", "State");
                            });
                }).build())
                .toPact();
    }

    @SneakyThrows
    @Test
    @PactTestFor(pactMethod = "validRestaurantEventFromKafkaProvider")
    public void testValidRestaurantEventFromProvider(List<Message> messages) {
        assertThat(messages).isNotEmpty();

        String messageContent = new String(messages.get(0).contentsAsBytes());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> messageMap = objectMapper.readValue(messageContent, new TypeReference<>() {
        });

        assertThat(messageMap)
                .containsEntry("message", "Restaurant created");

        Map<String, Object> restaurantDtoMap = (Map<String, Object>) messageMap.get("restaurantDto");

        assertThat(restaurantDtoMap)
                .containsEntry("id", "1")
                .containsEntry("userId", "1")
                .containsEntry("name", "Restaurant")
                .containsEntry("street", "Gade")
                .containsEntry("city", "Valby")
                .containsEntry("zipCode", "2500")
                .containsEntry("phone", "12345678")
                .containsEntry("country", "Denmark")
                .containsEntry("homepage", "www.homepage.com")
                .containsEntry("restaurantState", "State");
    }

}
