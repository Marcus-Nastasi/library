package com.app.library.adapters.input.member;

import com.app.library.domain.entity.member.MemberType;

import java.time.LocalDate;
import java.util.UUID;

public record MemberRequestDto(
        UUID id,
        String name,
        String cpf,
        MemberType type,
        LocalDate dateOfMembership,
        int booksIssued,
        int booksLimit,
        String phone
) {}
