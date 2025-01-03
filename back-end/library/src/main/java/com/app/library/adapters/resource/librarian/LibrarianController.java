package com.app.library.adapters.resource.librarian;

import com.app.library.adapters.input.librarian.LibrarianRequestDto;
import com.app.library.adapters.mapper.librarian.LibrarianDtoMapper;
import com.app.library.adapters.output.librarian.LibrarianResponseDto;
import com.app.library.application.usecases.librarian.LibrarianUseCase;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/librarian")
@SecurityRequirement(name = "Bearer Authentication")
public class LibrarianController {

    private final LibrarianUseCase librarianUseCase;
    private final LibrarianDtoMapper librarianDtoMapper;

    public LibrarianController(LibrarianUseCase librarianUseCase, LibrarianDtoMapper librarianDtoMapper) {
        this.librarianUseCase = librarianUseCase;
        this.librarianDtoMapper = librarianDtoMapper;
    }

    @GetMapping()
    @Cacheable("librarians")
    public LibrarianPaginated getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return librarianUseCase.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Cacheable("librarians")
    public LibrarianResponseDto get(@PathVariable UUID id) {
        return librarianDtoMapper.mapToResponse(librarianUseCase.get(id));
    }

    @GetMapping("cpf/{cpf}")
    @Cacheable("librarians")
    public Librarian getByCpf(@PathVariable String cpf) {
        return librarianUseCase.getByCpf(cpf);
    }

    @GetMapping("name/{name}")
    @Cacheable("librarians")
    public LibrarianPaginated getByName(@PathVariable String name, @RequestParam("page") int page, @RequestParam("size") int size) {
        return librarianUseCase.getByName(name, page, size);
    }

    @PostMapping(value = "/register")
    @CacheEvict(value = "librarians", allEntries = true)
    public ResponseEntity<LibrarianResponseDto> register(@RequestBody @Valid LibrarianRequestDto librarianRequestDto) {
        Librarian created = librarianUseCase.create(librarianDtoMapper.mapFromRequest(librarianRequestDto));
        return ResponseEntity.created(URI.create("/api/librarian/" + created.getId())).body(librarianDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    @CacheEvict(value = "librarians", allEntries = true)
    public ResponseEntity<LibrarianResponseDto> update(@PathVariable UUID id, @RequestBody @Valid LibrarianRequestDto librarianRequestDto) {
        Librarian updated = librarianUseCase.update(id, librarianDtoMapper.mapFromRequest(librarianRequestDto));
        return ResponseEntity.ok(librarianDtoMapper.mapToResponse(updated));
    }

    @DeleteMapping(value = "/delete/{id}")
    @CacheEvict(value = "librarians", allEntries = true)
    public ResponseEntity<LibrarianResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(librarianDtoMapper.mapToResponse(librarianUseCase.delete(id)));
    }
}
