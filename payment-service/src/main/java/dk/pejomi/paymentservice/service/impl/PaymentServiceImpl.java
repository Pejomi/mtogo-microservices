package dk.pejomi.paymentservice.service.impl;

import dk.pejomi.basedomain.dto.OrderDto;
import dk.pejomi.basedomain.dto.PaymentResponseDto;
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
    public PaymentResponseDto sendPaymentRequest(OrderDto orderDto) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999)
                .usePlaintext()
                .build();

        OrderPaymentServiceGrpc.OrderPaymentServiceBlockingStub stub
                = OrderPaymentServiceGrpc.newBlockingStub(channel);

        OrderPayment.OrderDto protoOrderDto = convertToProtoOrderDto(orderDto);

        OrderPayment.PaymentResponse response = stub.processPayment(protoOrderDto);
        channel.shutdown();

        return PaymentResponseDto.builder()
                .message(response.getMessage())
                .success(response.getSuccess())
                .orderDto(convertToOrderDto(response.getOrderDto()))
                .build();
    }

    private OrderPayment.OrderDto convertToProtoOrderDto(OrderDto orderDto) {
        return OrderPayment.OrderDto.newBuilder()
                .setId(orderDto.getId())
                .setConsumerId(orderDto.getConsumerId())
                .setRestaurantId(orderDto.getRestaurantId())
                .setOrderState(orderDto.getOrderState())
                .setPrice(orderDto.getPrice())
                .addAllOrderItemsDto(orderDto.getOrderItemsDto().stream()
                        .map(orderItemDto -> OrderPayment.OrderItemDto.newBuilder()
                                .setId(orderItemDto.getId())
                                .setMenuItemId(orderItemDto.getMenuItemId())
                                .setPrice(orderItemDto.getPrice())
                                .setQuantity(orderItemDto.getQuantity())
                                .build())
                        .toList())
                .build();
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
