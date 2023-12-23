package dk.pejomi.paymentgrpcclient;

import dk.pejomi.orderPaymentGRPC.OrderPayment;
import dk.pejomi.orderPaymentGRPC.OrderPaymentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Main {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999)
                .usePlaintext()
                .build();

        OrderPaymentServiceGrpc.OrderPaymentServiceBlockingStub stub
                = OrderPaymentServiceGrpc.newBlockingStub(channel);

        OrderPayment.OrderDto orderDto = OrderPayment.OrderDto.newBuilder()
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

        OrderPayment.PaymentResponse response = stub.processPayment(orderDto);
        System.out.println(response.getMessage());
    }
}
