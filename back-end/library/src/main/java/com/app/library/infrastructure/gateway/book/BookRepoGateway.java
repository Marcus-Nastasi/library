package com.app.library.infrastructure.gateway.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.book.BookType;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.infrastructure.mapper.book.BookEntityMapper;
import com.app.library.infrastructure.persistence.book.JpaBookRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
    public BookPaginated getAll(int page, int size) {
        return bookEntityMapper.mapToBookPaginated(jpaBookRepo.findAll(PageRequest.of(page, size)));
    }

    @Override
    public Book get(UUID id) {
        return bookEntityMapper.mapFromBookEntity(jpaBookRepo.findById(id).orElseThrow(() -> new DomainException("Book not found")));
    }

    @Override
    public BookPaginated getByName(String name, int page, int size) {
        return bookEntityMapper.mapToBookPaginated(jpaBookRepo.findByNameContaining(name, PageRequest.of(page, size)));
    }

    @Override
    public BookPaginated getByAuthor(String author, int page, int size) {
        return bookEntityMapper.mapToBookPaginated(jpaBookRepo.findByAuthorContaining(author, PageRequest.of(page, size)));
    }

    @Override
    public BookPaginated getByType(BookType bookType, int page, int size) {
        return bookEntityMapper.mapToBookPaginated(jpaBookRepo.findByType(bookType, PageRequest.of(page, size)));
    }

    @Override
    public List<Book> getByDateOfPublish(LocalDate localDate) {
        return jpaBookRepo.findByDateOfPublish(localDate).stream().map(bookEntityMapper::mapFromBookEntity).toList();
    }

    @Override
    public Book save(Book book) {
        return bookEntityMapper.mapFromBookEntity(jpaBookRepo.save(bookEntityMapper.mapToBookEntity(book)));
    }

    @Override
    public Book delete(UUID id) {
        Book book = bookEntityMapper.mapFromBookEntity(jpaBookRepo.findById(id).orElseThrow(() -> new DomainException("Book not found")));
        jpaBookRepo.deleteById(id);
        return book;
    }
}
