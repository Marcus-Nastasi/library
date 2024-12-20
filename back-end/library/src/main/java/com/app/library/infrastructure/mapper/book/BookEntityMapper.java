package com.app.library.infrastructure.mapper.book;

import com.app.library.domain.entity.book.Book;
import com.app.library.infrastructure.entity.book.BookEntity;

public class BookEntityMapper {
    public BookEntity mapToBookEntity(Book book) {
        return new BookEntity(
            book.getId(),
            book.getAuthor(),
            book.getName(),
            book.getPrice(),
            book.getQuantity(),
            book.getImage_url(),
            book.isAvailable(),
            book.getType(),
            book.getEdition(),
            book.getDateOfPublish()
        );
    }

    public Book mapFromBookEntity(BookEntity book) {
        return new Book(
            book.getId(),
            book.getAuthor(),
            book.getName(),
            book.getPrice(),
            book.getQuantity(),
            book.getImage_url(),
            book.isAvailable(),
            book.getType(),
            book.getEdition(),
            book.getDateOfPublish()
        );
    }
}
