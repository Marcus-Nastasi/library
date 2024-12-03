package com.app.library.application.gateways.librarian;

import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;

import java.util.UUID;

public interface LibrarianGateway {
    LibrarianPaginated getAll(int page, int size);
    Librarian get(UUID id);
    Librarian create(Librarian librarian);
    Librarian update(Librarian librarian);
    Librarian delete(UUID id);
}
