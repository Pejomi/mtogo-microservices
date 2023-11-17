package dk.pejomi.consumerservice.controller;

import dk.pejomi.consumerservice.dto.ConsumerDTO;
import dk.pejomi.consumerservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @PostMapping
    public ResponseEntity<ConsumerDTO> createConsumer(@RequestBody ConsumerDTO consumerDTO) {
        return new ResponseEntity<>(consumerService.createConsumer(consumerDTO), HttpStatus.CREATED);
    }
}
