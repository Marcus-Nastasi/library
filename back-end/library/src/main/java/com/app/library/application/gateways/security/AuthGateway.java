package com.app.library.application.gateways.security;

import com.app.library.domain.entity.librarian.Librarian;

public interface AuthGateway {
    Librarian getByCpf(String cpf);
    String login(String cpf);
}
