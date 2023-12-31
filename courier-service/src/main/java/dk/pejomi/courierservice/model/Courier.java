package dk.pejomi.courierservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("couriers")
public class Courier {

    @Id
    private Long id;
    private String vehicle;
    private String firstname;
    private String lastname;
    private String country;
    private String zipCode;
    private String phone;
    private String email;
    private boolean active;
}
