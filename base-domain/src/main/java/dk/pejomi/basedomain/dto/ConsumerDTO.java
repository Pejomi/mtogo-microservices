package dk.pejomi.basedomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConsumerDTO {
    private String username;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String zipCode;
    private String country;
}
