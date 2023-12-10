package dk.pejomi.consumerservice.kafka;

import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.consumerservice.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import static org.mockito.Mockito.*;

class UserConsumerTest {


    @Mock
    private ConsumerService consumerService;

    @InjectMocks
    private UserConsumer userConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsume() {

        ConsumerDto consumerDto = ConsumerDto.builder()
                .phone("12345678")
                .street("test street")
                .city("test city")
                .zipCode("1234")
                .country("test country")
                .build();

        // Create a test create consumer event
        CreateConsumerEvent testEvent = CreateConsumerEvent.builder()
                .message("test message")
                .consumerDTO(consumerDto)
                .build();

        // Call the consume method with the test event
        userConsumer.consume(testEvent);


        // Verify that the consumerService.createConsumer method was called with the expected argument
        verify(consumerService).createConsumer(testEvent.getConsumerDTO());
    }
}