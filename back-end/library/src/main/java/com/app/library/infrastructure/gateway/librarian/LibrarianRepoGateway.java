package com.app.library.infrastructure.gateway.librarian;

import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.infrastructure.mapper.librarian.LibrarianEntityMapper;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;

import java.util.List;
import java.util.UUID;

public class LibrarianRepoGateway implements LibrarianGateway {
    private final JpaLibrarianRepo jpaLibrarianRepo;
    private final LibrarianEntityMapper librarianEntityMapper;

    public LibrarianRepoGateway(JpaLibrarianRepo jpaBookRepo, LibrarianEntityMapper librarianEntityMapper) {
        this.jpaLibrarianRepo = jpaBookRepo;
        this.librarianEntityMapper = librarianEntityMapper;
    }

    @Override
    public List<Librarian> getAll() {
        return jpaLibrarianRepo.findAll().stream().map(librarianEntityMapper::mapFromLibrarianEntity).toList();
    }

    @Override
    public Librarian get(UUID id) {
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Librarian create(Librarian librarian) {
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.save(librarianEntityMapper.mapToLibrarianEntity(librarian)));
    }

    @Override
    public Librarian update(Librarian librarian) {
        return librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.save(librarianEntityMapper.mapToLibrarianEntity(librarian)));
    }

    @Override
    public Librarian delete(UUID id) {
        Librarian librarian = librarianEntityMapper.mapFromLibrarianEntity(jpaLibrarianRepo.findById(id).orElseThrow());
        jpaLibrarianRepo.deleteById(id);
        return librarian;
    }
}
