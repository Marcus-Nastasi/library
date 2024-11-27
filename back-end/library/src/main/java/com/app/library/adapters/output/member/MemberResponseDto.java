package com.app.library.adapters.output.member;

import com.app.library.domain.entity.member.MemberType;
import com.app.library.domain.entity.rent.Rent;

import java.time.LocalDate;
import java.util.List;
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
) {}
