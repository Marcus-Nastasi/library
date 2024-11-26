package com.app.library.infrastructure.gateway.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;
import com.app.library.infrastructure.entities.book.BookEntity;
import com.app.library.infrastructure.mappers.book.BookEntityMapper;
import com.app.library.infrastructure.persistence.book.JpaBookRepo;

import java.util.List;

public class BookRepoGateway implements BookGateway {
    private final JpaBookRepo jpaBookRepo;
    private final BookEntityMapper bookEntityMapper;

    public BookRepoGateway(JpaBookRepo jpaBookRepo, BookEntityMapper bookEntityMapper) {
        this.jpaBookRepo = jpaBookRepo;
        this.bookEntityMapper = bookEntityMapper;
    }

    @Override
    public List<Book> getAll() {
        return jpaBookRepo.findAll().stream().map(bookEntityMapper::mapFromBookEntity).toList();
    }

    @Override
    public Book create(Book book) {
        BookEntity bookEntity = bookEntityMapper.mapToBookEntity(book);
        BookEntity saved = jpaBookRepo.save(bookEntity);
        return bookEntityMapper.mapFromBookEntity(saved);
    }
}
