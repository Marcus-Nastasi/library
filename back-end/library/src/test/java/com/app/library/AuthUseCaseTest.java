package com.app.library;

import com.app.library.application.gateways.security.AuthGateway;
import com.app.library.application.usecases.security.AuthUseCase;
import com.app.library.application.usecases.security.PasswordUseCase;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.infrastructure.exception.ForbiddenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthUseCaseTest {
    @Mock
    private AuthGateway authGateway;
    @Mock
    private PasswordUseCase passwordUseCase;
    @InjectMocks
    private AuthUseCase authUseCase;

    Librarian librarian1 = new Librarian();
    Librarian librarian2 = new Librarian();

    @Test
    void getByCpf() {
        librarian1.setCpf(UUID.randomUUID().toString());
        librarian2.setCpf(UUID.randomUUID().toString());

        when(authGateway.getByCpf(librarian1.getCpf())).thenReturn(librarian1);
        when(authGateway.getByCpf(librarian2.getCpf())).thenReturn(null);

        assertEquals(librarian1, authUseCase.getByCpf(librarian1.getCpf()));
        assertNull(authUseCase.getByCpf(librarian2.getCpf()));

        verify(authGateway, times(2)).getByCpf(any(String.class));
    }

    @Test
    void login() {
        librarian1.setCpf("123");
        librarian1.setPassword("123");
        librarian2.setCpf(UUID.randomUUID().toString());

        when(authGateway.getByCpf(librarian1.getCpf())).thenReturn(librarian1);
        when(authGateway.getByCpf(librarian2.getCpf())).thenReturn(null);
        when(passwordUseCase.matches(librarian1.getPassword(), librarian1.getPassword())).thenReturn(true);
        when(authGateway.login(librarian1.getCpf())).thenReturn("123");

        assertEquals("123", authUseCase.login(librarian1.getPassword(), librarian1.getPassword()));
        assertThrows(ForbiddenException.class, () -> authUseCase.login(librarian2.getCpf(), librarian2.getPassword()));

        verify(authGateway, times(2)).getByCpf(any(String.class));
    }
}
