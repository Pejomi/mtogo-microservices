package dk.pejomi.paymentservice.service.impl;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.orderPaymentGRPC.OrderPayment;
import dk.pejomi.orderPaymentGRPC.OrderPaymentServiceGrpc;
import dk.pejomi.paymentservice.service.PaymentService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Override
    public OrderPayment.PaymentResponse sendPaymentRequest(OrderDto orderDto) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999)
                .usePlaintext()
                .build();

        OrderPaymentServiceGrpc.OrderPaymentServiceBlockingStub stub
                = OrderPaymentServiceGrpc.newBlockingStub(channel);
        OrderPayment.OrderDto protoOrderDto = OrderPayment.OrderDto.newBuilder()
                .setId(1)
                .setConsumerId(1)
                .setRestaurantId(1)
                .setOrderState("CREATED")
                .setPrice(100)
                .addOrderItemsDto(OrderPayment.OrderItemDto.newBuilder()
                        .setId(1)
                        .setMenuItemId(1)
                        .setPrice(100)
                        .setQuantity(1)
                        .build())
                .build();

        OrderPayment.PaymentResponse response = stub.processPayment(protoOrderDto);
        System.out.println(response.getMessage());

        return null;
    }
}
