package com.app.library.infrastructure.mapper.book;

import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.infrastructure.entity.book.BookEntity;
import org.springframework.data.domain.Page;

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

    public BookPaginated mapToBookPaginated(Page<BookEntity> bookPage) {
        return new BookPaginated(
            bookPage.getNumber(),
            bookPage.getSize(),
            bookPage.getTotalPages(),
            bookPage.getContent().stream().map(this::mapFromBookEntity).toList()
        );
    }
}
