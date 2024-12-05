package com.app.library.adapters.output.rent;

import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.member.Member;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record RentResponseDto(
        UUID id,
        LocalDate emit_date,
        LocalDate return_date,
        boolean returned,
        UUID librarian_id,
        Book book,
        Member member
) implements Serializable {}
