package com.app.library.application.usecases.security;

import com.app.library.application.exception.ForbiddenException;
import com.app.library.application.gateways.security.AuthGateway;
import com.app.library.application.gateways.util.PasswordEncoderGateway;
import com.app.library.domain.entity.librarian.Librarian;

public class AuthUseCase {
    private final AuthGateway authGateway;
    private final PasswordEncoderGateway encoder;

    public AuthUseCase(AuthGateway authGateway, PasswordEncoderGateway encoder) {
        this.authGateway = authGateway;
        this.encoder = encoder;
    }

    public Librarian getByCpf(String cpf) {
        return authGateway.getByCpf(cpf);
    }

    public String login(String cpf, String password) {
        Librarian librarian = getByCpf(cpf);
        if (librarian == null) throw new ForbiddenException("Librarian not found");
        if (!encoder.matches(password, librarian.getPassword()))
            throw new ForbiddenException("Wrong password");
        return authGateway.login(cpf);
    }
}
