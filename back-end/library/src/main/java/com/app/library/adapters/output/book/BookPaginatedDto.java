package com.app.library.adapters.output.book;

import com.app.library.domain.entity.book.Book;

import java.util.List;

public record BookPaginatedDto(
        int page,
        int size,
        int total,
        List<Book> books
) {}
