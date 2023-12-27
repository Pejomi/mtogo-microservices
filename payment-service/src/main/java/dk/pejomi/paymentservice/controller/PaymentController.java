package dk.pejomi.paymentservice.controller;


import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.PaymentResponseDto;
import dk.pejomi.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController implements PaymentApi {

    private final PaymentService paymentService;

    @Override
    @PostMapping
    public ResponseEntity<PaymentResponseDto> sendPaymentRequest(OrderDto orderDto) {
        try {
            return ResponseEntity.ok(paymentService.sendPaymentRequest(orderDto));
        } catch (RuntimeException e) {
            log.error("Error occurred during the payment request", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
