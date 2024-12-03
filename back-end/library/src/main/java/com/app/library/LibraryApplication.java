package com.app.library;

import com.app.library.infrastructure.gateway.security.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@EnableWebSecurity
@EnableCaching
@EnableJpaRepositories(basePackages = "com.app.library.infrastructure.persistence")
public class LibraryApplication {
	@Autowired
	private TokenFilter tokenFilter;

    public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

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
}
