package dk.pejomi.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long consumerId;
    @Column(nullable = false)
    private Long restaurantId;
    @Column(nullable = false)
    private String orderState;
    @Column(nullable = false, scale = 2, length = 100000)
    private double price;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    

}
