package com.app.library.infrastructure.gateway.librarian;

import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;
import com.app.library.infrastructure.entity.librarian.LibrarianEntity;
import com.app.library.infrastructure.exception.InfraException;
import com.app.library.infrastructure.mapper.librarian.LibrarianEntityMapper;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public class LibrarianRepoGateway implements LibrarianGateway {

    private final JpaLibrarianRepo jpaLibrarianRepo;
    private final LibrarianEntityMapper librarianEntityMapper;

    public LibrarianRepoGateway(JpaLibrarianRepo jpaBookRepo, LibrarianEntityMapper librarianEntityMapper) {
        this.jpaLibrarianRepo = jpaBookRepo;
        this.librarianEntityMapper = librarianEntityMapper;
    }

    @Override
    public LibrarianPaginated getAll(int page, int size) {
        return librarianEntityMapper.mapToLibrarianPaginated(jpaLibrarianRepo.findAll(PageRequest.of(page, size)));
    }

    @Override
    public Librarian get(UUID id) {
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.findById(id).orElseThrow(() -> new InfraException("librarian not found")));
    }

    @Override
    public Librarian getByCpf(String cpf) {
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.findByCpf(cpf));
    }

    @Override
    public LibrarianPaginated getByName(String name, int page, int size) {
        return librarianEntityMapper.mapToLibrarianPaginated(jpaLibrarianRepo.findByNameContaining(name, PageRequest.of(page, size)));
    }

    @Override
    public Librarian create(Librarian librarian) {
        LibrarianEntity found = jpaLibrarianRepo.findByCpf(librarian.getCpf());
        if (found != null) throw new InfraException("this CPF is already on our records, you cannot register the same CPF again");
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.save(librarianEntityMapper.mapToLibrarianEntity(librarian)));
    }

    @Override
    public Librarian update(Librarian librarian) {
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.save(librarianEntityMapper.mapToLibrarianEntity(librarian)));
    }

    @Override
    public Librarian delete(UUID id) {
        Librarian librarian = librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.findById(id).orElseThrow(() -> new InfraException("librarian not found")));
        jpaLibrarianRepo.deleteById(id);
        return librarian;
    }
}
