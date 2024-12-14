package com.app.library.application.gateways.book;

import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;

import java.util.UUID;

public interface BookGateway {

    BookPaginated getAll(int page, int size);

    Book get(UUID id);

    Book getByName(String name);

    Book create(Book book);

    Book update(Book book);

    Book delete(UUID id);
}
