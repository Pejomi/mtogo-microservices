package dk.pejomi.paymentservice.controller;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.PaymentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Payment", description = "MTOGO payment controller")
public interface PaymentApi {

    @Operation(
            summary = "Send payment request",
            description = "receives a payment request and sends by gRPC to the gRPC-server in order-service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request successful"),
            @ApiResponse(responseCode = "404", description = "Request unsuccessful"),
    })
    public ResponseEntity<PaymentResponseDto> sendPaymentRequest(@RequestBody OrderDto orderDto);
}
