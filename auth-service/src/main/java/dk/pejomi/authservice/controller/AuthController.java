package dk.pejomi.authservice.controller;

import dk.pejomi.authservice.model.AuthResponseDto;
import dk.pejomi.authservice.model.LoginDto;
import dk.pejomi.authservice.model.RegisterConsumerDto;
import dk.pejomi.authservice.model.RegisterRestaurantDto;
import dk.pejomi.authservice.service.LoginService;
import dk.pejomi.authservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final RegisterService registerService;
    private final LoginService loginService;


    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            AuthResponseDto authResponse = loginService.login(loginDto);
            return ResponseEntity.ok(authResponse);
        } catch (AuthenticationException e) {
            // Handle authentication failure here
            log.error("Authentication failed for user [{}]", loginDto.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            // Handle other exceptions if needed
            log.error("Exception occurred while authenticating user [{}]", loginDto.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/register/consumer")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> registerConsumer(@RequestBody RegisterConsumerDto registerConsumerDto) {
        if (!registerService.isEmailAvailable(registerConsumerDto.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(registerService.registerConsumer(registerConsumerDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Exception occurred while registering consumer [{}]", registerConsumerDto.getEmail(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/restaurant")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> registerRestaurant(@RequestBody RegisterRestaurantDto registerRestaurantDto) {
        if (!registerService.isEmailAvailable(registerRestaurantDto.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(registerService.registerRestaurant(registerRestaurantDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Exception occurred while registering restaurant [{}]", registerRestaurantDto.getEmail(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
