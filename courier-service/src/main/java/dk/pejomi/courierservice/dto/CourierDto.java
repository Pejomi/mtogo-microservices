package dk.pejomi.courierservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CourierDto {
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
