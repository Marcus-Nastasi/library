package com.app.library.infrastructure.persistence.book;

import com.app.library.infrastructure.entity.book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaBookRepo extends JpaRepository<BookEntity, UUID> {

    BookEntity findByName(String name);
}
