package com.app.library.adapters.output.rent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record RentResponseDto(
        UUID id,
        UUID book_id,
        LocalDate emit_date,
        LocalDate return_date,
        UUID librarian_id,
        UUID member_id
) implements Serializable {}
