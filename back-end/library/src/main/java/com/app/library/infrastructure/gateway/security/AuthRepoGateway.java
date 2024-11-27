package com.app.library.infrastructure.gateway.security;

import com.app.library.application.gateways.security.AuthGateway;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.infrastructure.mapper.librarian.LibrarianEntityMapper;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;

public class AuthRepoGateway implements AuthGateway {
    private final LibrarianEntityMapper librarianEntityMapper;
    private final JpaLibrarianRepo jpaLibrarianRepo;
    private final TokenProvider tokenProvider;

    public AuthRepoGateway(LibrarianEntityMapper librarianEntityMapper, JpaLibrarianRepo jpaLibrarianRepo, TokenProvider tokenProvider) {
        this.librarianEntityMapper = librarianEntityMapper;
        this.jpaLibrarianRepo = jpaLibrarianRepo;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Librarian getByCpf(String cpf) {
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.findByCpf(cpf));
    }

    @Override
    public String login(String cpf) {
        return tokenProvider.generate(cpf);
    }
}
