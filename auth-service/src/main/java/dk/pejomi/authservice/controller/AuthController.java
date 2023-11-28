package dk.pejomi.authservice.controller;

import dk.pejomi.authservice.model.AuthResponseDto;
import dk.pejomi.authservice.model.LoginDto;
import dk.pejomi.authservice.model.RegisterDto;
import dk.pejomi.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            AuthResponseDto authResponse = userService.login(loginDto);
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
    public ResponseEntity<String> registerConsumer(@RequestBody RegisterDto registerDto) {
        if (userService.checkEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(userService.registerConsumer(registerDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Exception occurred while registering consumer [{}]", registerDto.getEmail(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register/restaurant")
    public ResponseEntity<String> registerRestaurant(@RequestBody RegisterDto registerDto) {
        if (userService.checkEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(userService.registerRestaurant(registerDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.error("Exception occurred while registering restaurant [{}]", registerDto.getEmail(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
