package com.app.library.application.usecases.librarian;

import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.domain.entity.librarian.Librarian;

import java.util.List;
import java.util.UUID;

public class LibrarianUseCase {
    public final LibrarianGateway librarianGateway;

    public LibrarianUseCase(LibrarianGateway librarianGateway) {
        this.librarianGateway = librarianGateway;
    }

    public List<Librarian> getAll() {
        return librarianGateway.getAll();
    }

    public Librarian get(UUID id) {
        return librarianGateway.get(id);
    }

    public Librarian create(Librarian book) {
        return librarianGateway.create(book);
    }

    public Librarian update(UUID id, Librarian book) {
        Librarian toUpdate = get(id);
        return librarianGateway.update(toUpdate.updateDetails(book));
    }

    public Librarian delete(UUID id) {
        Librarian librarian = get(id);
        librarianGateway.delete(id);
        return librarian;
    }
}
