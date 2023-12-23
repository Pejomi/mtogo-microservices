package dk.pejomi.orderservice.service.impl;

import dk.pejomi.orderservice.orderPaymentGRPC.OrderPaymentServiceGrpc;
import dk.pejomi.orderservice.orderPaymentGRPC.OrderPayment;
import dk.pejomi.orderservice.orderPaymentGRPC.OrderPaymentServiceGrpc;

public class OrderPaymentImpl extends OrderPaymentServiceGrpc.OrderPaymentServiceImplBase{
    @Override
    public void processPayment(OrderPayment.OrderDto request, io.grpc.stub.StreamObserver<OrderPayment.PaymentResponse> responseObserver) {
        // Implement your payment processing logic here
        // You can access the order details using request.getId(), request.getConsumerId(), etc.

        // For simplicity, let's assume the payment is always successful
        OrderPayment.PaymentResponse response = OrderPayment.PaymentResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Payment successful")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
