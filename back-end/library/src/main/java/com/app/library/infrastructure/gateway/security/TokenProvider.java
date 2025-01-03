package com.app.library.infrastructure.gateway.security;

import com.app.library.infrastructure.exception.InfraException;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenProvider {

    @Value("${spring.security.token.secret}")
    private String secret;

    public String generate(String cpf) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                .create()
                .withIssuer("library-api")
                .withSubject(cpf)
                .withExpiresAt(exp())
                .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new InfraException(e.getMessage());
        }
    }

    public String validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT
                .require(algorithm)
                .withIssuer("library-api")
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant exp() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
