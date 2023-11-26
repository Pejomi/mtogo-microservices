package dk.pejomi.consumerservice.controller;

import dk.pejomi.basedomain.dto.ConsumerDto;
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
    public ResponseEntity<ConsumerDto> createConsumer(@RequestBody ConsumerDto consumerDTO) {
        return new ResponseEntity<>(consumerService.createConsumer(consumerDTO), HttpStatus.CREATED);
    }
}
