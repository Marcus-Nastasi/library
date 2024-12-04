package com.app.library.infrastructure.gateway.security;

import com.app.library.application.gateways.security.PasswordEncoderGateway;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderRepoGateway implements PasswordEncoderGateway {
    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderRepoGateway(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String raw, String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }
}
