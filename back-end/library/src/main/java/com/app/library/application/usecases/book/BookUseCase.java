package com.app.library.application.usecases.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;

import java.util.List;

public class BookUseCase {
    private final BookGateway bookGateway;

    public BookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    public List<Book> getAll() {
        return bookGateway.getAll();
    }
    public Book create(Book book) {
        return bookGateway.create(book);
    }

    /*
    public Book deleteById(UUID id) {
        return repo.findById(id).orElseThrow(RuntimeException::new).mapToBook();
    }*/
}
