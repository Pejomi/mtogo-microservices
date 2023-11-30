package dk.pejomi.authservice.service;

import dk.pejomi.authservice.model.AuthResponseDto;
import dk.pejomi.authservice.model.LoginDto;

public interface LoginService {
    AuthResponseDto login(LoginDto loginDto);
}
