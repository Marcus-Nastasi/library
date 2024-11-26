package com.app.library.application.gateways.auth;

import com.app.library.domain.entity.librarian.Librarian;

public interface AuthGateway {
    Librarian login(String name, String password);
}
