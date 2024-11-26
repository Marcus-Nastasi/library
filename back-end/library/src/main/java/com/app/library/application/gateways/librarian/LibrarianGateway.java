package com.app.library.application.gateways.librarian;

import com.app.library.domain.entity.librarian.Librarian;

import java.util.List;
import java.util.UUID;

public interface LibrarianGateway {
    List<Librarian> getAll();
    Librarian get(UUID id);
    Librarian create(Librarian librarian);
    Librarian update(Librarian librarian);
    Librarian delete(UUID id);
}
