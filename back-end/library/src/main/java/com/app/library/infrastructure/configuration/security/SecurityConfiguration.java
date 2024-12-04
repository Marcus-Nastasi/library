package com.app.library.infrastructure.configuration.security;

import com.app.library.application.gateways.security.AuthGateway;
import com.app.library.application.gateways.security.PasswordEncoderGateway;
import com.app.library.application.usecases.security.AuthUseCase;
import com.app.library.application.usecases.security.PasswordUseCase;
import com.app.library.infrastructure.gateway.security.AuthRepoGateway;
import com.app.library.infrastructure.gateway.security.TokenFilter;
import com.app.library.infrastructure.gateway.security.TokenProvider;
import com.app.library.infrastructure.gateway.security.PasswordEncoderRepoGateway;
import com.app.library.infrastructure.mapper.librarian.LibrarianEntityMapper;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(h -> {
                h.requestMatchers("/api/authenticate").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                    .requestMatchers("/api/librarian/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
            }).addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

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
    public AuthUseCase authUseCase(AuthGateway authGateway, PasswordUseCase passwordUseCase) {
        return new AuthUseCase(authGateway, passwordUseCase);
    }

    @Bean
    public PasswordUseCase passwordUseCase(PasswordEncoderGateway passwordEncoderGateway) {
        return new PasswordUseCase(passwordEncoderGateway);
    }

    @Bean
    public PasswordEncoderGateway passwordEncoderGateway(PasswordEncoder passwordEncoder) {
        return new PasswordEncoderRepoGateway(passwordEncoder);
    }
}
