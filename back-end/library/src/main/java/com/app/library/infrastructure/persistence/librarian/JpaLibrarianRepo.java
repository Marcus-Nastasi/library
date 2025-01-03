package com.app.library.infrastructure.persistence.librarian;

import com.app.library.infrastructure.entity.librarian.LibrarianEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaLibrarianRepo extends JpaRepository<LibrarianEntity, UUID> {

    LibrarianEntity findByCpf(String cpf);

    Page<LibrarianEntity> findByNameContaining(String name, Pageable pageable);
}
