package com.app.library.infrastructure.gateway.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.infrastructure.mapper.book.BookEntityMapper;
import com.app.library.infrastructure.persistence.book.JpaBookRepo;

import java.util.List;
import java.util.UUID;

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
    public Book get(UUID id) {
        return bookEntityMapper.mapFromBookEntity(jpaBookRepo.findById(id).orElseThrow(() -> new DomainException("Book not found")));
    }

    @Override
    public Book create(Book book) {
        return bookEntityMapper.mapFromBookEntity(jpaBookRepo.save(bookEntityMapper.mapToBookEntity(book)));
    }

    @Override
    public Book update(Book book) {
        return bookEntityMapper.mapFromBookEntity(jpaBookRepo.save(bookEntityMapper.mapToBookEntity(book)));
    }

    @Override
    public Book delete(UUID id) {
        Book book = bookEntityMapper
            .mapFromBookEntity(jpaBookRepo.findById(id).orElseThrow(() -> new DomainException("Book not found")));
        jpaBookRepo.deleteById(id);
        return book;
    }
}
