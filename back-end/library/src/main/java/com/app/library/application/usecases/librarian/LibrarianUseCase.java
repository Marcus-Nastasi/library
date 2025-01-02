package com.app.library.application.usecases.librarian;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.application.usecases.security.PasswordUseCase;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;
import com.app.library.domain.entity.librarian.UserRole;

import java.util.List;
import java.util.UUID;

public class LibrarianUseCase {

    private final LibrarianGateway librarianGateway;
    private final PasswordUseCase passwordUseCase;

    public LibrarianUseCase(LibrarianGateway librarianGateway, PasswordUseCase passwordUseCase) {
        this.librarianGateway = librarianGateway;
        this.passwordUseCase = passwordUseCase;
    }

    public LibrarianPaginated getAll(int page, int size) {
        return librarianGateway.getAll(page, size);
    }

    public Librarian get(UUID id) {
        return librarianGateway.get(id);
    }

    public Librarian getByCpf(String cpf) {
        return librarianGateway.getByCpf(cpf);
    }

    public List<Librarian> getByName(String name) {
        return librarianGateway.getByName(name);
    }

    public Librarian create(Librarian librarian) {
        librarian.setPassword(passwordUseCase.encode(librarian.getPassword()));
        librarian.setRole(UserRole.USER);
        return librarianGateway.create(librarian);
    }

    public Librarian update(UUID id, Librarian librarian) {
        Librarian toUpdate = get(id);
        librarian.setPassword(passwordUseCase.encode(librarian.getPassword()));
        return librarianGateway.update(toUpdate.updateDetails(librarian));
    }

    public Librarian delete(UUID id) {
        Librarian toDelete = get(id);
        if (toDelete == null) throw new ApplicationException("librarian not found");
        return librarianGateway.delete(id);
    }
}
