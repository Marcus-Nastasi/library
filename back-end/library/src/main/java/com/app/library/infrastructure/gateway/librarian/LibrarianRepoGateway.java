package com.app.library.infrastructure.gateway.librarian;

import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;
import com.app.library.infrastructure.entity.librarian.LibrarianEntity;
import com.app.library.infrastructure.mapper.librarian.LibrarianEntityMapper;
import com.app.library.infrastructure.persistence.librarian.JpaLibrarianRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
    public LibrarianPaginated getAll(int page, int size) {
        Page<LibrarianEntity> librarianEntities = jpaLibrarianRepo.findAll(PageRequest.of(page, size));
        return new LibrarianPaginated(
            librarianEntities.getNumber(),
            librarianEntities.getSize(),
            librarianEntities.getTotalPages(),
            librarianEntities.map(librarianEntityMapper::mapFromLibrarianEntity).toList()
        );
    }

    @Override
    public Librarian get(UUID id) {
        return librarianEntityMapper
            .mapFromLibrarianEntity(jpaLibrarianRepo.findById(id).orElseThrow(() -> new DomainException("librarian not found")));
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
        Librarian librarian = librarianEntityMapper
            .mapFromLibrarianEntity(jpaLibrarianRepo.findById(id).orElseThrow(() -> new DomainException("librarian not found")));
        jpaLibrarianRepo.deleteById(id);
        return librarian;
    }
}
