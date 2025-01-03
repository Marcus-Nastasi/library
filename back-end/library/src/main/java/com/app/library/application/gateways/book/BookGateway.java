package com.app.library.application.gateways.book;

import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.book.BookType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookGateway {

    BookPaginated getAll(int page, int size);

    Book get(UUID id);

    BookPaginated getByName(String name, int page, int size);

    BookPaginated getByAuthor(String author, int page, int size);

    BookPaginated getByType(BookType bookType, int page, int size);

    List<Book> getByDateOfPublish(LocalDate localDate);

    Book save(Book book);

    Book delete(UUID id);
}
