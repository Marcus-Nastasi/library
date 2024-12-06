package com.app.library.application.usecases.librarian;

import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.application.gateways.security.PasswordEncoderGateway;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;
import com.app.library.domain.entity.librarian.UserRole;

import java.util.UUID;

public class LibrarianUseCase {
    private final LibrarianGateway librarianGateway;
    private final PasswordEncoderGateway passwordEncoderGateway;

    public LibrarianUseCase(LibrarianGateway librarianGateway, PasswordEncoderGateway passwordEncoderGateway) {
        this.librarianGateway = librarianGateway;
        this.passwordEncoderGateway = passwordEncoderGateway;
    }

    public LibrarianPaginated getAll(int page, int size) {
        return librarianGateway.getAll(page, size);
    }

    public Librarian get(UUID id) {
        return librarianGateway.get(id);
    }

    public Librarian create(Librarian librarian) {
        librarian.setPassword(passwordEncoderGateway.encode(librarian.getPassword()));
        librarian.setRole(UserRole.USER);
        return librarianGateway.create(librarian);
    }

    public Librarian update(UUID id, Librarian librarian) {
        Librarian toUpdate = get(id);
        toUpdate.setPassword(passwordEncoderGateway.encode(librarian.getPassword()));
        return librarianGateway.update(toUpdate.updateDetails(librarian));
    }

    public Librarian delete(UUID id) {
        return librarianGateway.delete(id);
    }
}
