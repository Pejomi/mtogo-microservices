package dk.pejomi.authservice.service.impl;

import dk.pejomi.authservice.kafka.CreateConsumerProducer;
import dk.pejomi.authservice.kafka.CreateRestaurantProducer;
import dk.pejomi.authservice.model.RegisterConsumerDto;
import dk.pejomi.authservice.model.RegisterRestaurantDto;
import dk.pejomi.authservice.model.Role;
import dk.pejomi.authservice.model.User;
import dk.pejomi.authservice.repository.RoleRepository;
import dk.pejomi.authservice.repository.UserRepository;
import dk.pejomi.authservice.service.RegisterService;
import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final CreateConsumerProducer createConsumerProducer;
    private final CreateRestaurantProducer createRestaurantProducer;

    @Override
    public String registerConsumer(RegisterConsumerDto registerConsumerDto) {
        User user = new User();
        user.setEmail(registerConsumerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerConsumerDto.getPassword()));

        Role userRole = roleRepository.findByName("CONSUMER").orElseThrow(() -> new RuntimeException("User role not found"));
        user.setRoles(Collections.singletonList(userRole));

        // Persist user
        User newUser;
        try {
            newUser = userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error registering user");
        }


        // Prepare consumerDTO
        ConsumerDto consumerDTO = ConsumerDto.builder()
                .id(newUser.getId())
                .phone(registerConsumerDto.getPhone())
                .street(registerConsumerDto.getStreet())
                .city(registerConsumerDto.getCity())
                .zipCode(registerConsumerDto.getZipCode())
                .country(registerConsumerDto.getCountry())
                .build();


        // Send event to consumer-service with kafka
        CreateConsumerEvent createConsumerEvent = CreateConsumerEvent.builder()
                .message("Create consumer event")
                .consumerDTO(consumerDTO)
                .build();

        createConsumerProducer.sendCreateConsumer(createConsumerEvent);

        return "User registered successfully";
    }

    @Override
    public String registerRestaurant(RegisterRestaurantDto registerRestaurantDto) {
        User user = new User();
        user.setEmail(registerRestaurantDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRestaurantDto.getPassword()));

        Role userRole = roleRepository.findByName("RESTAURANT").orElseThrow(() -> new RuntimeException("User role not found"));
        user.setRoles(Collections.singletonList(userRole));

        // Persist user
        User newUser;
        try {
            newUser = userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error registering user");
        }

        // Prepare restaurantDTO
        RestaurantDto restaurantDTO = RestaurantDto.builder()
                .id(newUser.getId())
                .name(registerRestaurantDto.getName())
                .phone(registerRestaurantDto.getPhone())
                .street(registerRestaurantDto.getStreet())
                .city(registerRestaurantDto.getCity())
                .zipCode(registerRestaurantDto.getZipCode())
                .country(registerRestaurantDto.getCountry())
                .homepage(registerRestaurantDto.getHomepage())
                .restaurantState("PENDING")
                .build();

        // Send event to restaurant-service with kafka
        CreateRestaurantEvent createRestaurantEvent = CreateRestaurantEvent.builder()
                .message("Create restaurant event")
                .restaurantDto(restaurantDTO)
                .build();

        createRestaurantProducer.sendCreateRestaurant(createRestaurantEvent);


        return "User registered successfully";
    }


    @Override
    public Boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
