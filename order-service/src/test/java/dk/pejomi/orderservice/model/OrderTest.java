package dk.pejomi.orderservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    @Test
    public void testOrderEntity() {
        // Create test data
        OrderItem orderItem1 = OrderItem.builder()
                .menuItemId(1L)
                .price(10.00)
                .quantity(2)
                .build();
        OrderItem orderItem2 = OrderItem.builder()
                .menuItemId(2L)
                .price(15.00)
                .quantity(1)
                .build();

        // Create an order with order items
        Order order = Order.builder()
                .consumerId(1L)
                .restaurantId(2L)
                .orderState("CREATED")
                .orderItems(Arrays.asList(orderItem1, orderItem2))
                .price(50.00)
                .build();

        // Set the order for each order item
        orderItem1.setOrder(order);
        orderItem2.setOrder(order);


        // Assert that the order has been created correctly
        assertThat(order.getConsumerId()).isEqualTo(1L);
        assertThat(order.getRestaurantId()).isEqualTo(2L);
        assertThat(order.getOrderState()).isEqualTo("CREATED");
        assertThat(order.getPrice()).isEqualTo(50.00);
        assertThat(order.getOrderItems()).containsExactly(orderItem1, orderItem2);

        // Test cascading operations
        assertThat(orderItem1.getOrder()).isEqualTo(order);
        assertThat(orderItem2.getOrder()).isEqualTo(order);
    }
}
