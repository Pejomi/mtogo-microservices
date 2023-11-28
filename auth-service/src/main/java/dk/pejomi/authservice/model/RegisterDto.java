package dk.pejomi.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String email;
    private String password;
    private String phone;
    private String street;
    private String city;
    private String zipCode;
    private String country;

}
