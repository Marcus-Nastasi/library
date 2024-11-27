package com.app.library.adapters.output.rent;

import com.app.library.domain.entity.member.Member;

import java.time.LocalDate;
import java.util.UUID;

public record RentResponseDto(
        UUID id,
        UUID book_id,
        LocalDate emit_date,
        LocalDate return_date,
        UUID librarian_id,
        UUID member_id
) {}
