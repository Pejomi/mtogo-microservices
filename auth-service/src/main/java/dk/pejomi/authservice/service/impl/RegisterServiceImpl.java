package dk.pejomi.authservice.service.impl;

import dk.pejomi.authservice.kafka.UserProducer;
import dk.pejomi.authservice.model.RegisterConsumerDto;
import dk.pejomi.authservice.model.RegisterRestaurantDto;
import dk.pejomi.authservice.model.Role;
import dk.pejomi.authservice.model.User;
import dk.pejomi.authservice.repository.RoleRepository;
import dk.pejomi.authservice.repository.UserRepository;
import dk.pejomi.authservice.service.RegisterService;
import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.basedomain.event.CreateConsumerEvent;
import dk.pejomi.basedomain.event.CreateRestaurantEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final RuntimeService runtimeService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserProducer userProducer;

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

        userProducer.sendCreateConsumer(createConsumerEvent);

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

        userProducer.sendCreateRestaurant(createRestaurantEvent);


        return "User registered successfully";
    }

    @Override
    public String camundaRegisterRestaurant(RegisterRestaurantDto registerRestaurantDto) {

// Prepare request body for camunda
        Map<String, Object> requestBodyMap  = new HashMap<>();
        Map<String, Object> variables = new HashMap<>();

        Map<String, Object> emailVariable = new HashMap<>();
        Map<String, Object> passwordVariable = new HashMap<>();
        Map<String, Object> nameVariable = new HashMap<>();
        Map<String, Object> phoneVariable = new HashMap<>();
        Map<String, Object> streetVariable = new HashMap<>();
        Map<String, Object> cityVariable = new HashMap<>();
        Map<String, Object> zipCodeVariable = new HashMap<>();
        Map<String, Object> countryVariable = new HashMap<>();
        Map<String, Object> homepageVariable = new HashMap<>();
        Map<String, Object> restaurantStateVariable = new HashMap<>();


        emailVariable.put("value", registerRestaurantDto.getEmail());
        passwordVariable.put("value", registerRestaurantDto.getPassword());
        nameVariable.put("value", registerRestaurantDto.getName());
        phoneVariable.put("value", registerRestaurantDto.getPhone());
        streetVariable.put("value", registerRestaurantDto.getStreet());
        cityVariable.put("value", registerRestaurantDto.getCity());
        zipCodeVariable.put("value", registerRestaurantDto.getZipCode());
        countryVariable.put("value", registerRestaurantDto.getCountry());
        homepageVariable.put("value", registerRestaurantDto.getHomepage());
        restaurantStateVariable.put("value", "PENDING");

        variables.put("email", emailVariable);
        variables.put("password", passwordVariable);
        variables.put("name", nameVariable);
        variables.put("phone", phoneVariable);
        variables.put("street", streetVariable);
        variables.put("city", cityVariable);
        variables.put("zipCode", zipCodeVariable);
        variables.put("country", countryVariable);
        variables.put("homepage", homepageVariable);
        variables.put("restaurantState", restaurantStateVariable);

        requestBodyMap.put("variables", variables);

        // Request for starting process in camunda
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String startCamundaProcessURL = "http://localhost:8080/engine-rest/process-definition/key/createRestaurantProcess/start";

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBodyMap, headers);

        ResponseEntity<String> httpResponse = restTemplate.postForEntity(startCamundaProcessURL, requestEntity, String.class);
        System.out.println(httpResponse.getBody());

        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(5000) // long polling timeout
                .build();

        client.subscribe("createRestaurant")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {

                    // Business logic
                    registerRestaurant(registerRestaurantDto);

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();

        return "Restaurant registered successfully";
    }


    @Override
    public Boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

}
