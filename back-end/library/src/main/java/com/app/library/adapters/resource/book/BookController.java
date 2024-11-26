package com.app.library.adapters.resource.book;

import com.app.library.adapters.input.book.BookRequestDto;
import com.app.library.adapters.mapper.book.BookDtoMapper;
import com.app.library.adapters.output.book.BookResponseDto;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.domain.entity.book.Book;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {
    private final BookUseCase bookUseCase;
    private final BookDtoMapper bookDtoMapper;

    public BookController(BookUseCase bookUseCase, BookDtoMapper bookDtoMapper) {
        this.bookUseCase = bookUseCase;
        this.bookDtoMapper = bookDtoMapper;
    }

    @GetMapping()
    public List<Book> getAll() {
        return bookUseCase.getAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable UUID id) {
        return bookUseCase.get(id);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<BookResponseDto> register(@RequestBody @Valid BookRequestDto bookRequestDto) {
        Book created = bookUseCase.create(bookDtoMapper.mapFromRequest(bookRequestDto));
        return ResponseEntity
            .created(URI.create("/api/book/" + created.getId()))
            .body(bookDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<BookResponseDto> update(@PathVariable UUID id, @RequestBody @Valid BookRequestDto bookRequestDto) {
        return ResponseEntity
            .ok(bookDtoMapper.mapToResponse(bookUseCase.update(id, bookDtoMapper.mapFromRequest(bookRequestDto))));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<BookResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(bookDtoMapper.mapToResponse(bookUseCase.delete(id)));
    }
}
