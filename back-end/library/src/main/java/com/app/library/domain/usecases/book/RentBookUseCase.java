package com.app.library.domain.usecases.book;

import com.app.library.domain.entities.Book;
import com.app.library.domain.repositories.BookRepository;

public class RentBookUseCase {
    private final BookRepository bookRepository;

    public RentBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void rentBook(String bookId, String memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already rented");
        }
        book.setAvailable(false);
        bookRepository.save(book);
    }
}
