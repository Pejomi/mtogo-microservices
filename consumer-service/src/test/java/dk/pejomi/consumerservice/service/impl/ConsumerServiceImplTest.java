package dk.pejomi.consumerservice.service.impl;

import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.consumerservice.model.Consumer;
import dk.pejomi.consumerservice.repository.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceImplTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @InjectMocks
    private ConsumerServiceImpl consumerService;

    private Consumer consumer;
    private ConsumerDto consumerDTO;

    @BeforeEach
    void setUp() {
        consumerDTO = ConsumerDto.builder()
                .id(1L)
                .phone("12345678")
                .city("Copenhagen")
                .country("Denmark")
                .street("Street 1")
                .zipCode("1234")
                .build();

        consumer = Consumer.builder()
                .id(1L)
                .phone("12345678")
                .city("Copenhagen")
                .country("Denmark")
                .street("Street 1")
                .zipCode("1234")
                .build();
    }

    @Test
    void should_return_consumer_when_creating_consumer() {
        //Arrange
        when(consumerRepository.save(any(Consumer.class))).thenReturn(consumer);

        //Act
        ConsumerDto actual = consumerService.createConsumer(consumerDTO);

        //Assert
        assertEquals(consumerDTO.toString(), actual.toString());
    }
}