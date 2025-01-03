package com.app.library.infrastructure.persistence.book;

import com.app.library.domain.entity.book.BookType;
import com.app.library.infrastructure.entity.book.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface JpaBookRepo extends JpaRepository<BookEntity, UUID> {

    Page<BookEntity> findByNameContaining(String name, Pageable pageable);

    Page<BookEntity> findByAuthorContaining(String author, Pageable pageable);

    Page<BookEntity> findByType(BookType bookType, Pageable pageable);

    Page<BookEntity> findByDateOfPublish(LocalDate localDate, Pageable pageable);
}
