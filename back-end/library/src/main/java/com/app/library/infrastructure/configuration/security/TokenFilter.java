package com.app.library.infrastructure.configuration.security;

import com.app.library.application.exception.ForbiddenException;
import com.app.library.infrastructure.entity.librarian.LibrarianEntity;
import com.app.library.infrastructure.gateway.security.TokenProvider;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final JpaLibrarianRepo jpaLibrarianRepo;

    public TokenFilter(TokenProvider tokenProvider, JpaLibrarianRepo jpaLibrarianRepo) {
        this.tokenProvider = tokenProvider;
        this.jpaLibrarianRepo = jpaLibrarianRepo;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain
    ) {
        String token = recover(request);
        try {
            if (token != null) {
                String cpf = tokenProvider.validate(token);
                LibrarianEntity u = jpaLibrarianRepo.findByCpf(cpf);
                var auth = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private String recover(HttpServletRequest request) {
        String headers = request.getHeader("Authorization");
        if (headers == null) return null;
        return headers.replace("Bearer ", "");
    }
}
