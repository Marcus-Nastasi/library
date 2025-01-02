package com.app.library.infrastructure.gateway.security;

import com.app.library.infrastructure.exception.ForbiddenException;
import com.app.library.application.gateways.security.AuthGateway;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.infrastructure.entity.librarian.LibrarianEntity;
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
        LibrarianEntity librarian = jpaLibrarianRepo.findByCpf(cpf);
        if (librarian == null) throw new ForbiddenException("Librarian not found");
        return librarianEntityMapper.mapFromLibrarianEntity(librarian);
    }

    @Override
    public String login(String cpf) {
        return tokenProvider.generate(cpf);
    }
}
