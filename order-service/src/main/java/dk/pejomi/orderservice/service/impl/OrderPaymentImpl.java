package dk.pejomi.orderservice.service.impl;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.orderPaymentGRPC.OrderPayment;
import dk.pejomi.orderPaymentGRPC.OrderPaymentServiceGrpc;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderPaymentImpl extends OrderPaymentServiceGrpc.OrderPaymentServiceImplBase{

    @Override
    public void processPayment(OrderPayment.OrderDto request, io.grpc.stub.StreamObserver<OrderPayment.PaymentResponse> responseObserver) {

        boolean paymentSuccess = true;
        OrderPayment.PaymentResponse response;
        String createOrderURL = "http://localhost:8080/api/order";

        if (paymentSuccess) {
            // Payment successful
            try {
                // Request for creating order in order-service
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();

                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<OrderDto> requestEntity = new HttpEntity<>(convertToOrderDto(request), headers);

                ResponseEntity<OrderDto> httpResponse = restTemplate.postForEntity(createOrderURL, requestEntity, OrderDto.class);
                System.out.println(httpResponse.getBody());

            } catch (Exception e) {
                System.out.println("Error occurred during the order creation");
            }

            response = OrderPayment.PaymentResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Payment successful")
                    .setOrderDto(request)
                    .build();
        } else {
            // Payment failed
            response = OrderPayment.PaymentResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Payment failed")
                    .setOrderDto(request)
                    .build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public OrderDto convertToOrderDto(OrderPayment.OrderDto protoOrderDto) {
        return OrderDto.builder()
                .id(protoOrderDto.getId())
                .consumerId(protoOrderDto.getConsumerId())
                .restaurantId(protoOrderDto.getRestaurantId())
                .orderState(protoOrderDto.getOrderState())
                .price(protoOrderDto.getPrice())
                .orderItemsDto(protoOrderDto.getOrderItemsDtoList().stream()
                        .map(orderItemDto -> dk.pejomi.basedomain.dto.OrderItemDto.builder()
                                .id(orderItemDto.getId())
                                .menuItemId(orderItemDto.getMenuItemId())
                                .price(orderItemDto.getPrice())
                                .quantity(orderItemDto.getQuantity())
                                .build())
                        .toList())
                .build();
    }
}
