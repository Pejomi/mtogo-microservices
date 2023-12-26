package dk.pejomi.paymentservice.service;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.PaymentResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
        PaymentResponseDto sendPaymentRequest(OrderDto orderDto);
}
