package com.app.library.infrastructure.mapper.librarian;

import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;
import com.app.library.infrastructure.entity.librarian.LibrarianEntity;
import org.springframework.data.domain.Page;

public class LibrarianEntityMapper {

    public LibrarianEntity mapToLibrarianEntity(Librarian librarian) {
        return new LibrarianEntity(
            librarian.getId(),
            librarian.getName(),
            librarian.getCpf(),
            librarian.getPassword(),
            librarian.getRole()
        );
    }

    public Librarian mapFromLibrarianEntity(LibrarianEntity librarianEntity) {
        return new Librarian(
            librarianEntity.getId(),
            librarianEntity.getName(),
            librarianEntity.getCpf(),
            librarianEntity.getPassword(),
            librarianEntity.getRole()
        );
    }

    public LibrarianPaginated mapToLibrarianPaginated(Page<LibrarianEntity> librarians) {
        return new LibrarianPaginated(
            librarians.getNumber(),
            librarians.getSize(),
            librarians.getTotalPages(),
            librarians.map(this::mapFromLibrarianEntity).toList()
        );
    }
}
