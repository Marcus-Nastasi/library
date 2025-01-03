package com.app.library.adapters.output.book;

import com.app.library.domain.entity.book.BookType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record BookResponseDto(
        UUID id,
        String author,
        String name,
        double price,
        int quantity,
        String image_url,
        boolean isAvailable,
        BookType type,
        String edition,
        LocalDate dateOfPublish
) implements Serializable {}
