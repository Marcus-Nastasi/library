package com.app.library.application.gateways.book;

import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;

import java.util.List;
import java.util.UUID;

public interface BookGateway {

    BookPaginated getAll(int page, int size);

    Book get(UUID id);

    List<Book> getByName(String name);

    List<Book> getByAuthor(String author);

    Book save(Book book);

    Book delete(UUID id);
}
