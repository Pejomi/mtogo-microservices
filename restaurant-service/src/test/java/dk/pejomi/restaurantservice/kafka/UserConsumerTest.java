package dk.pejomi.restaurantservice.kafka;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import dk.pejomi.restaurantservice.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class UserConsumerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private UserConsumer userConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsume() {
        // Arrange
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .name("test name")
                .street("test street")
                .city("test city")
                .zipCode("1234")
                .country("test country")
                .build();

        CreateRestaurantEvent testEvent = CreateRestaurantEvent.builder()
                .message("test message")
                .restaurantDto(restaurantDto)
                .build();

        // Act
        userConsumer.consume(testEvent);

        // Assert
        verify(restaurantService).createRestaurant(testEvent.getRestaurantDto());
    }

}