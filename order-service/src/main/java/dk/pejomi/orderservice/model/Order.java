package dk.pejomi.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String consumerId;
    private String orderState;
    private double price;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems;
}
