package com.app.library.infrastructure.configuration.security;

import com.app.library.application.gateways.security.AuthGateway;
import com.app.library.application.usecases.security.AuthUseCase;
import com.app.library.infrastructure.gateway.security.AuthRepoGateway;
import com.app.library.infrastructure.gateway.security.TokenProvider;
import com.app.library.infrastructure.mapper.librarian.LibrarianEntityMapper;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeans {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration ac) throws Exception {
        return ac.getAuthenticationManager();
    }

    @Bean
    public AuthGateway authGateway(
            LibrarianEntityMapper librarianEntityMapper,
            JpaLibrarianRepo jpaLibrarianRepo,
            TokenProvider tokenProvider
    ) {
        return new AuthRepoGateway(librarianEntityMapper, jpaLibrarianRepo, tokenProvider);
    }

    @Bean
    public AuthUseCase authUseCase(AuthGateway authGateway) {
        return new AuthUseCase(authGateway, passwordEncoder());
    }
}
