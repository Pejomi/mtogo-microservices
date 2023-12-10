package dk.pejomi.authservice.service.impl;

import dk.pejomi.authservice.model.AuthResponseDto;
import dk.pejomi.authservice.model.LoginDto;
import dk.pejomi.authservice.util.JwtGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtGenerator jwtGenerator;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    void testLogin_Success() {
        // Arrange
        LoginDto loginDto = new LoginDto("test@example.com", "password");
        Authentication authenticationMock = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticationMock);
        when(jwtGenerator.generateToken(authenticationMock)).thenReturn("dummyToken");

        // Act
        AuthResponseDto authResponseDto = loginService.login(loginDto);

        // Assert
        assertEquals("dummyToken", authResponseDto.getAccessToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtGenerator).generateToken(authenticationMock);
        verifyNoMoreInteractions(authenticationManager, jwtGenerator);
    }

    @Test
    void testLogin_Failure() {
        // Arrange
        LoginDto loginDto = new LoginDto("test@example.com", "wrongPassword");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Test exception") {
                });

        // Act and Assert
        try {
            loginService.login(loginDto);
        } catch (BadCredentialsException e) {
            assertEquals("Invalid username/password supplied", e.getMessage());
        }

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoMoreInteractions(authenticationManager, jwtGenerator);
    }
}