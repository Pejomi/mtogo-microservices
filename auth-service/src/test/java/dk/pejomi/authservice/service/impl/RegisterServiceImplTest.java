package dk.pejomi.authservice.service.impl;

import dk.pejomi.authservice.config.SecurityConfig;
import dk.pejomi.authservice.kafka.UserProducer;
import dk.pejomi.authservice.model.RegisterConsumerDto;
import dk.pejomi.authservice.model.RegisterRestaurantDto;
import dk.pejomi.authservice.model.Role;
import dk.pejomi.authservice.model.User;
import dk.pejomi.authservice.repository.RoleRepository;
import dk.pejomi.authservice.repository.UserRepository;
import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Import(SecurityConfig.class)
class RegisterServiceImplTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Mock
    private UserProducer userProducer;

    @InjectMocks
    private RegisterServiceImpl registerService;

    private static User user;
    private static Role consumerRole;
    private static Role restaurantRole;
    private static RegisterConsumerDto registerConsumerDto;
    private static RegisterRestaurantDto registerRestaurantDto;

    @BeforeAll
    static void beforeAll() {
        user = User.builder()
                .id(1L)
                .email("test@mail.dk")
                .password("password")
                .build();

        consumerRole = Role.builder()
                .id(1)
                .name("CONSUMER")
                .build();

        restaurantRole = Role.builder()
                .id(2)
                .name("RESTAURANT")
                .build();

        registerConsumerDto = RegisterConsumerDto.builder()
                .email("test@mail.dk")
                .password("password")
                .phone("12345678")
                .street("street")
                .city("city")
                .zipCode("zipCode")
                .country("country")
                .build();

        registerRestaurantDto = RegisterRestaurantDto.builder()
                .email("test@mail.dk")
                .password("password")
                .phone("12345678")
                .street("street")
                .city("city")
                .zipCode("zipCode")
                .country("country")
                .homepage("www.homepage.dk")
                .restaurantState("PENDING")
                .build();


    }

    // Consumer tests
    @Test
    void should_register_consumer_when_register_consumer_is_called() {
        // Arrange
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(roleRepository.findByName(Mockito.anyString())).thenReturn(java.util.Optional.of(consumerRole));
        when(userProducer.sendCreateConsumer(Mockito.any(CreateConsumerEvent.class))).thenReturn(new CreateConsumerEvent());
        // Act

        String actual = registerService.registerConsumer(registerConsumerDto);

        // Assert
        assertNotNull(actual);
    }

    @Test
    void should_not_register_consumer_when_register_consumer_is_called_with_invalid_email() {
        // Arrange
        when(roleRepository.findByName(Mockito.anyString())).thenReturn(java.util.Optional.of(consumerRole));
        when(userRepository.save(Mockito.any(User.class))).thenThrow(RuntimeException.class);

        // Act
        String actual = "";
        try {
            actual = registerService.registerConsumer(registerConsumerDto);
        } catch (Exception e) {

            // Assert
            assertEquals("Error registering user", e.getMessage());
        }
        assertEquals("", actual);
    }

    @Test
    void should_not_register_consumer_when_register_consumer_is_called_with_invalid_role() {
        // Arrange
        when(roleRepository.findByName(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        String actual = "";

        // Act
        try {
            registerService.registerConsumer(registerConsumerDto);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        // Assert
        assertEquals("User role not found", actual);
    }


    // Restaurant tests
    @Test
    void should_register_restaurant_when_register_restaurant_is_called() {
        // Arrange
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(roleRepository.findByName(Mockito.anyString())).thenReturn(java.util.Optional.of(restaurantRole));
        when(userProducer.sendCreateRestaurant(Mockito.any(CreateRestaurantEvent.class))).thenReturn(new CreateRestaurantEvent());
        // Act

        String actual = registerService.registerRestaurant(registerRestaurantDto);

        // Assert
        assertNotNull(actual);
    }

    @Test
    void should_not_register_restaurant_when_register_restaurant_is_called_with_invalid_email() {
        // Arrange
        when(roleRepository.findByName(Mockito.anyString())).thenReturn(java.util.Optional.of(restaurantRole));
        when(userRepository.save(Mockito.any(User.class))).thenThrow(RuntimeException.class);

        // Act
        String actual = "";
        try {
            actual = registerService.registerRestaurant(registerRestaurantDto);
        } catch (Exception e) {

            // Assert
            assertEquals("Error registering user", e.getMessage());
        }
        assertEquals("", actual);
    }

    @Test
    void should_not_register_restaurant_when_register_restaurant_is_called_with_invalid_role() {
        // Arrange
        when(roleRepository.findByName(Mockito.anyString())).thenReturn(java.util.Optional.empty());
        String actual = "";

        // Act
        try {
            registerService.registerRestaurant(registerRestaurantDto);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        // Assert
        assertEquals("User role not found", actual);
    }

    @Test
    void should_return_true_when_email_is_available() {
        // Arrange
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        // Act
        Boolean actual = registerService.isEmailAvailable(user.getEmail());

        // Assert
        assertEquals(true, actual);
    }

    @Test
    void should_return_false_when_email_is_not_available() {
        // Arrange
        when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        // Act
        Boolean actual = registerService.isEmailAvailable(user.getEmail());

        // Assert
        assertEquals(false, actual);
    }


}