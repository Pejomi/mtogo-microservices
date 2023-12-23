package dk.pejomi.orderservice.service.impl;

import dk.pejomi.orderPaymentGRPC.OrderPayment;
import dk.pejomi.orderPaymentGRPC.OrderPaymentServiceGrpc;

public class OrderPaymentImpl extends OrderPaymentServiceGrpc.OrderPaymentServiceImplBase{
    @Override
    public void processPayment(OrderPayment.OrderDto request, io.grpc.stub.StreamObserver<OrderPayment.PaymentResponse> responseObserver) {
        // Implement your payment processing logic here
        // You can access the order details using request.getId(), request.getConsumerId(), etc.

        // For simplicity, let's assume the payment is always successful
        OrderPayment.PaymentResponse response = OrderPayment.PaymentResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Payment successful")
//                .setMessage("Payment successful" + "\nID: " + request.getId() + "\nConsumer ID: " + request.getConsumerId() + "\nRestaurant ID: " + request.getRestaurantId() + "\nOrder State: " + request.getOrderState() + "\nPrice: " + request.getPrice() + "\nOrder Items: \n" + request.getOrderItemsDtoList())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
