package com.app.library.infrastructure.configuration.util;

import com.app.library.application.gateways.util.PasswordEncoderGateway;
import com.app.library.application.usecases.util.PasswordUseCase;
import com.app.library.infrastructure.gateway.util.PasswordEncoderRepoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UtilBeans {

    @Bean
    public PasswordUseCase passwordUseCase(PasswordEncoderGateway passwordEncoderGateway) {
        return new PasswordUseCase(passwordEncoderGateway);
    }

    @Bean
    public PasswordEncoderGateway passwordEncoderGateway(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderRepoGateway(passwordEncoder);
    }
}
