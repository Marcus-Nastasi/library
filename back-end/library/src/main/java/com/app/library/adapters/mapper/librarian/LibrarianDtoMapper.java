package com.app.library.adapters.mapper.librarian;

import com.app.library.adapters.input.librarian.LibrarianRequestDto;
import com.app.library.adapters.output.librarian.LibrarianResponseDto;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.infrastructure.entities.librarian.LibrarianEntity;

public class LibrarianDtoMapper {
    public LibrarianResponseDto mapToResponse(Librarian librarian) {
        return new LibrarianResponseDto(
            librarian.getId(),
            librarian.getName(),
            librarian.getCpf(),
            librarian.getPassword(),
            librarian.getRole()
        );
    }

    public Librarian mapFromRequest(LibrarianRequestDto librarianRequestDto) {
        return new Librarian(
            librarianRequestDto.id(),
            librarianRequestDto.name(),
            librarianRequestDto.cpf(),
            librarianRequestDto.password(),
            librarianRequestDto.userRole()
        );
    }
}
