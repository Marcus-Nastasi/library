package com.app.library.application.usecases.security;

import com.app.library.application.gateways.security.PasswordEncoderGateway;

public class PasswordUseCase {

    private final PasswordEncoderGateway passwordEncoder;

    public PasswordUseCase(PasswordEncoderGateway passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String raw, String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }
}
