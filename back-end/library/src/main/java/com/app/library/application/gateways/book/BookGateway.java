package com.app.library.application.gateways.book;

import com.app.library.domain.entity.book.Book;

import java.util.List;

public interface BookGateway {
    List<Book> getAll();
    Book create(Book book);
}
