package com.app.library.adapters.resource.librarian;

import com.app.library.adapters.input.librarian.LibrarianRequestDto;
import com.app.library.adapters.mapper.librarian.LibrarianDtoMapper;
import com.app.library.adapters.output.librarian.LibrarianResponseDto;
import com.app.library.application.usecases.librarian.LibrarianUseCase;
import com.app.library.domain.entity.librarian.Librarian;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/librarian")
public class LibrarianController {
    private final LibrarianUseCase librarianUseCase;
    private final LibrarianDtoMapper librarianDtoMapper;
    private final PasswordEncoder passwordEncoder;

    public LibrarianController(
            LibrarianUseCase librarianUseCase,
            LibrarianDtoMapper librarianDtoMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.librarianUseCase = librarianUseCase;
        this.librarianDtoMapper = librarianDtoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public ResponseEntity<List<LibrarianResponseDto>> getAll() {
        return ResponseEntity
            .ok(librarianUseCase.getAll().stream().map(librarianDtoMapper::mapToResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibrarianResponseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(librarianDtoMapper.mapToResponse(librarianUseCase.get(id)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<LibrarianResponseDto> register(@RequestBody @Valid LibrarianRequestDto librarianRequestDto) {
        Librarian toCreate = librarianDtoMapper.mapFromRequest(librarianRequestDto);
        toCreate.setPassword(passwordEncoder.encode(librarianRequestDto.password()));
        Librarian created = librarianUseCase.create(toCreate);
        return ResponseEntity
            .created(URI.create("/api/librarian/" + created.getId()))
            .body(librarianDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<LibrarianResponseDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid LibrarianRequestDto librarianRequestDto
    ) {
        return ResponseEntity.ok(librarianDtoMapper
            .mapToResponse(librarianUseCase
                .update(id, librarianDtoMapper.mapFromRequest(librarianRequestDto)))
        );
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<LibrarianResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(librarianDtoMapper.mapToResponse(librarianUseCase.delete(id)));
    }
}
