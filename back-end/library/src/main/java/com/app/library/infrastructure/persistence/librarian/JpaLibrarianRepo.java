package com.app.library.infrastructure.persistence.librarian;

import com.app.library.infrastructure.entities.librarian.LibrarianEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaLibrarianRepo extends JpaRepository<LibrarianEntity, UUID> {}
