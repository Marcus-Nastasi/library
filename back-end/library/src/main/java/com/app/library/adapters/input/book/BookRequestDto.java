package com.app.library.adapters.input.book;

import com.app.library.domain.entity.book.BookType;

import java.time.LocalDate;

public record BookRequestDto(
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
