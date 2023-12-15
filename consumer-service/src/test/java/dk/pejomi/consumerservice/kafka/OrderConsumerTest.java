package dk.pejomi.consumerservice.kafka;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.event.OrderEvent;
import dk.pejomi.consumerservice.service.ConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderConsumerTest {


    @Mock
    private ConsumerService consumerService;
    @InjectMocks
    private OrderConsumer orderConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsume() {

        OrderDto orderDto = OrderDto.builder()
                .consumerId(1L)
                .restaurantId(1L)
                .build();

        // Create a test order event
        OrderEvent testOrderEvent = OrderEvent.builder()
                .message("test message")
                .status("test status")
                .orderDto(orderDto)
                .build();

        // Call the consume method with the test event
        orderConsumer.consume(testOrderEvent);

        // Verify that the consumerService.increaseOrderCount method was called with the expected argument
        verify(consumerService).increaseOrderCount(testOrderEvent.getOrderDto().getConsumerId());
    }
}