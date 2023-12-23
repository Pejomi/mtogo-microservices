package dk.pejomi.paymentservice.service;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.orderPaymentGRPC.OrderPayment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

        OrderPayment.PaymentResponse sendPaymentRequest(OrderDto orderDto);
}
