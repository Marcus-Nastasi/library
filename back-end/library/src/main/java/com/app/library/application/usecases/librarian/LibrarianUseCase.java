package com.app.library.application.usecases.librarian;

import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.UserRole;

import java.util.List;
import java.util.UUID;

public class LibrarianUseCase {
    private final LibrarianGateway librarianGateway;

    public LibrarianUseCase(LibrarianGateway librarianGateway) {
        this.librarianGateway = librarianGateway;
    }

    public List<Librarian> getAll() {
        return librarianGateway.getAll();
    }

    public Librarian get(UUID id) {
        return librarianGateway.get(id);
    }

    public Librarian create(Librarian librarian) {
        librarian.setRole(UserRole.USER);
        return librarianGateway.create(librarian);
    }

    public Librarian update(UUID id, Librarian librarian) {
        Librarian toUpdate = get(id);
        return librarianGateway.update(toUpdate.updateDetails(librarian));
    }

    public Librarian delete(UUID id) {
        Librarian librarian = get(id);
        librarianGateway.delete(id);
        return librarian;
    }
}
