package dk.pejomi.menuservice.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, scale = 2, length = 1000)
    private double price;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    @ToString.Exclude
    private Menu menu;
}
