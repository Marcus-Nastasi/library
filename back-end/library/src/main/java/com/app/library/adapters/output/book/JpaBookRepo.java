package com.app.library.adapters.output.book;

import com.app.library.domain.entities.book.Book;
import com.app.library.infrastructure.entities.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaBookRepo extends JpaRepository<BookEntity, UUID> {}
