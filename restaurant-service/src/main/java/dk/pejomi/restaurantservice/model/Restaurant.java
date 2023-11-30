package dk.pejomi.restaurantservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String homepage;
    @Column(nullable = false)
    private String restaurantState;

}
