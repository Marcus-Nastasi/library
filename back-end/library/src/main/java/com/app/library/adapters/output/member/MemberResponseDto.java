package com.app.library.adapters.output.member;

import com.app.library.domain.entity.member.MemberType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record MemberResponseDto(
        UUID id,
        String name,
        String cpf,
        MemberType type,
        LocalDate dateOfMembership,
        int booksIssued,
        int booksLimit,
        String phone
) implements Serializable {}
