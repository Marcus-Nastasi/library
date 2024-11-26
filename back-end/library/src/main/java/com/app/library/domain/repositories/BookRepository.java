package com.app.library.domain.repositories;

import com.app.library.domain.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(String id);
    List<Book> findAll();
    void save(Book book);
}
