package com.app.library.infrastructure.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityFilter extends DelegatingWebMvcConfiguration {
    private final TokenFilter tokenFilter;

    public SecurityFilter(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(h -> {
                h.requestMatchers("/api/auth/login").permitAll()
                    .requestMatchers("/api/librarian/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
            })
            .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
