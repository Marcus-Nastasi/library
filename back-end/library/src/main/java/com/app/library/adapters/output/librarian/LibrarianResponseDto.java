package com.app.library.adapters.output.librarian;

import com.app.library.domain.entity.librarian.UserRole;

import java.util.UUID;

public record LibrarianResponseDto(
        UUID id,
        String name,
        String cpf,
        String password,
        UserRole userRole
) {}
