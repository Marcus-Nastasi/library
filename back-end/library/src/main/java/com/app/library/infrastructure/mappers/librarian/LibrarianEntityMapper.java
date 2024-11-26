package com.app.library.infrastructure.mappers.librarian;

import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.infrastructure.entities.librarian.LibrarianEntity;

public class LibrarianEntityMapper {
    public LibrarianEntity mapToLibrarianEntity(Librarian librarian) {
        return new LibrarianEntity(
            librarian.getId(),
            librarian.getName(),
            librarian.getPassword(),
            librarian.getCpf(),
            librarian.getRole()
        );
    }

    public Librarian mapFromLibrarianEntity(LibrarianEntity librarianEntity) {
        return new Librarian(
            librarianEntity.getId(),
            librarianEntity.getName(),
            librarianEntity.getPassword(),
            librarianEntity.getCpf(),
            librarianEntity.getRole()
        );
    }
}
