package com.app.library.adapters.input.book;

import com.app.library.domain.entity.book.BookType;

import java.time.LocalDate;
import java.util.UUID;

public record BookRequestDto(
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
) {}
