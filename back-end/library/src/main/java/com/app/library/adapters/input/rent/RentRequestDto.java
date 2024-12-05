package com.app.library.adapters.input.rent;

import com.app.library.domain.entity.member.Member;

import java.time.LocalDate;
import java.util.UUID;

public record RentRequestDto(
        UUID id,
        UUID book_id,
        LocalDate emit_date,
        LocalDate return_date,
        boolean returned,
        UUID librarian_id,
        UUID member_id,
        Member member
) {}
