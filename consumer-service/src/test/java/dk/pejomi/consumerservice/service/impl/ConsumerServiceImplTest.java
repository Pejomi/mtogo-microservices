package dk.pejomi.consumerservice.service.impl;

import dk.pejomi.consumerservice.dto.ConsumerDTO;
import dk.pejomi.consumerservice.model.Consumer;
import dk.pejomi.consumerservice.repository.ConsumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceImplTest {

    @Mock
    private ConsumerRepository consumerRepository;

    @InjectMocks
    private ConsumerServiceImpl consumerService;

    private Consumer consumer;
    private ConsumerDTO consumerDTO;

    @BeforeEach
    void setUp() {
        consumerDTO = ConsumerDTO.builder()
                .username("John")
                .email("john@doe.com")
                .phone("12345678")
                .city("Copenhagen")
                .country("Denmark")
                .street("Street 1")
                .zipCode("1234")
                .build();

        consumer = Consumer.builder()
                .id(1L)
                .username("John")
                .password("1234")
                .email("john@doe.com")
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
        ConsumerDTO actual = consumerService.createConsumer(consumerDTO);

        //Assert
        assertEquals(consumerDTO.toString(), actual.toString());
    }
}