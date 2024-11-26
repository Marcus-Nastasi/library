package com.app.library.application.usecases.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;

import java.util.List;
import java.util.UUID;

public class BookUseCase {
    private final BookGateway bookGateway;

    public BookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    public List<Book> getAll() {
        return bookGateway.getAll();
    }

    public Book get(UUID id) {
        return bookGateway.get(id);
    }

    public Book create(Book book) {
        return bookGateway.create(book);
    }

    public Book update(UUID id, Book book) {
        Book toUpdate = get(id);
        return bookGateway.update(toUpdate.updateDetails(book));
    }

    public Book delete(UUID id) {
        return bookGateway.delete(id);
    }
}
