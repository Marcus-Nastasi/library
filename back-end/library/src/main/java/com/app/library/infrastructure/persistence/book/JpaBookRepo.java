package com.app.library.infrastructure.persistence.book;

import com.app.library.infrastructure.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaBookRepo extends JpaRepository<BookEntity, UUID> {

    List<BookEntity> findByNameContaining(String name);

    List<BookEntity> findByAuthorContaining(String author);
}
