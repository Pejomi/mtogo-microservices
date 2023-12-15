package dk.pejomi.authservice.pact;

import au.com.dius.pact.core.model.Interaction;
import au.com.dius.pact.core.model.Pact;
import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.authservice.kafka.UserProducer;
import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;


@Provider("auth-service")
@Consumer("restaurant-service")
@PactBroker(host = "localhost", port = "9292")
@RequiredArgsConstructor
@Disabled
public class UserProducerTest {

    private final UserProducer userProducer;
    private CreateRestaurantEvent event;

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void testTemplate(Pact pact, Interaction interaction, PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 9292));

        event = CreateRestaurantEvent.builder()
                .message("Restaurant created")
                .restaurantDto(RestaurantDto.builder()
                        .id(1L)
                        .userId(1L)
                        .name("Restaurant")
                        .street("Gade")
                        .city("Valby")
                        .zipCode("2500")
                        .phone("12345678")
                        .country("Denmark")
                        .homepage("www.homepage.com")
                        .restaurantState("State")
                        .build())
                .build();
    }


    @SneakyThrows
    @PactVerifyProvider("valid restaurantEvent from kafka provider")
    public String verifyDateInformationMessage() {
        return new ObjectMapper().writeValueAsString(userProducer.sendCreateRestaurant(event));
    }

}
