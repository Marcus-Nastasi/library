package com.app.library.application.gateways.book;

import com.app.library.domain.entity.book.Book;

import java.util.List;
import java.util.UUID;

public interface BookGateway {
    List<Book> getAll();
    Book get(UUID id);
    Book create(Book book);
    Book update(Book book);
    Book delete(UUID id);
}
