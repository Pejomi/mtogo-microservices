package dk.pejomi.notificationservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@Slf4j
public class NotificationController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.info("Notification service is up and running");
        return ResponseEntity.ok("Notification service is up and running");
    }

    @GetMapping("/error")
    public ResponseEntity<String> error() {
        log.error("Notification service is down");
        return ResponseEntity.status(500).body("Notification service is down");
    }

    @GetMapping("/info/{number}")
    public ResponseEntity<String> info(@PathVariable int number) {
        int calculatedNumber = 0;

        for (int i = 0; i < number; i++) {
            calculatedNumber += i;
        }

        log.info("Magic number is {}", calculatedNumber);
        return ResponseEntity.status(200).body("Magic number is " + calculatedNumber);
    }
}
