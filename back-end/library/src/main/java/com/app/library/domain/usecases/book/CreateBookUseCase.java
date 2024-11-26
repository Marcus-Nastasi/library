package com.app.library.domain.usecases.book;


import com.app.library.domain.entities.Book;
import com.app.library.domain.repositories.BookRepository;

public class CreateBookUseCase {
    private final BookRepository bookRepository;

    public CreateBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }
}
