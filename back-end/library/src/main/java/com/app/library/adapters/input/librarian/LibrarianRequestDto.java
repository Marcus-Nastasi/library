package com.app.library.adapters.input.librarian;

import com.app.library.domain.entity.librarian.UserRole;

import java.util.UUID;

public record LibrarianRequestDto(
        UUID id,
        String name,
        String cpf,
        String password,
        UserRole role
) {}
