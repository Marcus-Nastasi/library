package com.app.library.infrastructure.persistence.librarian;

import com.app.library.infrastructure.entity.librarian.LibrarianEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface JpaLibrarianRepo extends JpaRepository<LibrarianEntity, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM librarians WHERE(cpf=?1)")
    LibrarianEntity findByCpf(String cpf);
}
