package dk.pejomi.basedomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ConsumerDto {
    private Long id;
    private String phone;
    private String street;
    private String city;
    private String zipCode;
    private String country;
}
