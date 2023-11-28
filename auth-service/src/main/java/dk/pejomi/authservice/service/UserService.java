package dk.pejomi.authservice.service;

import dk.pejomi.authservice.model.AuthResponseDto;
import dk.pejomi.authservice.model.LoginDto;
import dk.pejomi.authservice.model.RegisterDto;

public interface UserService {
    AuthResponseDto login(LoginDto loginDto);
    String registerConsumer(RegisterDto registerDto);
    String registerRestaurant(RegisterDto registerDto);
    Boolean checkEmail(String email);
}
