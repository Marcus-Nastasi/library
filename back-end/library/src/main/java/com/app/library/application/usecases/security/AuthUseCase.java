package com.app.library.application.usecases.security;

import com.app.library.application.gateways.security.AuthGateway;
import com.app.library.domain.entity.librarian.Librarian;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCase {
    private final AuthGateway authGateway;
    private final PasswordEncoder passwordEncoder;

    public AuthUseCase(AuthGateway authGateway, PasswordEncoder passwordEncoder) {
        this.authGateway = authGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public Librarian getByCpf(String cpf) {
        return authGateway.getByCpf(cpf);
    }

    public String login(String cpf, String password) {
        Librarian librarian = getByCpf(cpf);
        if (librarian == null) {
            return null;
        }
        if (!passwordEncoder.matches(password, librarian.getPassword())) {
            throw new RuntimeException("Password wrong");
        }
        return authGateway.login(cpf);
    }
}
