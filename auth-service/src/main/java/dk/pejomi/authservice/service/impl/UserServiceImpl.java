package dk.pejomi.authservice.service.impl;

import dk.pejomi.authservice.kafka.UserProducer;
import dk.pejomi.authservice.model.*;
import dk.pejomi.authservice.repository.RoleRepository;
import dk.pejomi.authservice.repository.UserRepository;
import dk.pejomi.authservice.service.UserService;
import dk.pejomi.authservice.util.JwtGenerator;
import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.basedomain.event.CreateConsumerEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    private final UserProducer userProducer;

    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new AuthResponseDto(token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @Override
    public String registerConsumer(RegisterDto registerDto) {
        try {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            Role userRole = roleRepository.findByName("CONSUMER").orElseThrow(() -> new RuntimeException("User role not found"));
            user.setRoles(Collections.singletonList(userRole));

            User newUser = userRepository.save(user);

            // Prepare consumerDTO
            ConsumerDto consumerDTO = ConsumerDto.builder()
                    .id(newUser.getId())
                    .phone(registerDto.getPhone())
                    .street(registerDto.getStreet())
                    .city(registerDto.getCity())
                    .zipCode(registerDto.getZipCode())
                    .country(registerDto.getCountry())
                    .build();


            // Send event to consumer-service with kafka
            CreateConsumerEvent createConsumerEvent = CreateConsumerEvent.builder()
                    .message("Create consumer event")
                    .consumerDTO(consumerDTO)
                    .build();

            userProducer.sendCreateConsumer(createConsumerEvent);

            return "User registered successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error registering user");
        }
    }

    @Override
    public String registerRestaurant(RegisterDto registerDto) {
        try {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            Role userRole = roleRepository.findByName("RESTAURANT").orElseThrow(() -> new RuntimeException("User role not found"));
            user.setRoles(Collections.singletonList(userRole));

            userRepository.save(user);
            return "User registered successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error registering user");
        }
    }

    @Override
    public Boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
