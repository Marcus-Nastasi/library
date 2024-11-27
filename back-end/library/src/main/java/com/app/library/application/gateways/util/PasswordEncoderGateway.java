package com.app.library.application.gateways.util;

public interface PasswordEncoderGateway {
    String encode(String password);
    boolean matches(String raw, String encoded);
}