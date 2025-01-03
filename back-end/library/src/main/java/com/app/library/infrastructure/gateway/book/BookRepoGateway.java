package com.app.library.infrastructure.gateway.book;

import com.app.library.application.gateways.book.BookGateway;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.book.BookType;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.infrastructure.entity.book.BookEntity;
import com.app.library.infrastructure.mapper.book.BookEntityMapper;
import com.app.library.infrastructure.persistence.book.JpaBookRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookRepoGateway implements BookGateway {

    private final JpaBookRepo jpaBookRepo;
    private final BookEntityMapper bookEntityMapper;

    public BookRepoGateway(JpaBookRepo jpaBookRepo, BookEntityMapper bookEntityMapper) {
        this.jpaBookRepo = jpaBookRepo;
        this.bookEntityMapper = bookEntityMapper;
    }

    @Override
    public BookPaginated getAll(int page, int size) {
        Page<BookEntity> bookEntityPage = jpaBookRepo.findAll(PageRequest.of(page, size));
        return new BookPaginated(
            bookEntityPage.getNumber(),
            bookEntityPage.getSize(),
            bookEntityPage.getTotalPages(),
            bookEntityPage.map(bookEntityMapper::mapFromBookEntity).toList()
        );
    }

    @Override
    public Book get(UUID id) {
        return bookEntityMapper.mapFromBookEntity(jpaBookRepo.findById(id).orElseThrow(() -> new DomainException("Book not found")));
    }

    @Override
    public List<Book> getByName(String name) {
        return jpaBookRepo.findByNameContaining(name).stream().map(bookEntityMapper::mapFromBookEntity).toList();
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return jpaBookRepo.findByAuthorContaining(author).stream().map(bookEntityMapper::mapFromBookEntity).toList();
    }

    @Override
    public BookPaginated getByType(BookType bookType, int page, int size) {
        Page<BookEntity> bookPage = jpaBookRepo.findByType(bookType, PageRequest.of(page, size));
        return new BookPaginated(
            bookPage.getNumber(),
            bookPage.getSize(),
            bookPage.getTotalPages(),
            bookPage.getContent().stream().map(bookEntityMapper::mapFromBookEntity).toList()
        );
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
