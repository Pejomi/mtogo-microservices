package dk.pejomi.authservice.model;

import lombok.Data;

@Data
public class LoginDto {

    private String username;
    private String password;
}
