package com.app.library.adapters.resource.book;

import com.app.library.adapters.input.book.BookRequestDto;
import com.app.library.adapters.mapper.book.BookDtoMapper;
import com.app.library.adapters.output.book.BookResponseDto;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.book.BookType;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDate;
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
    @Cacheable("books")
    public BookPaginated getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        if (page < 0) page = 0;
        if (size < 10) size = 10;
        return bookUseCase.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Cacheable("books")
    public Book get(@PathVariable UUID id) {
        return bookUseCase.get(id);
    }

    @PostMapping(value = "/register")
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<BookResponseDto> register(
            @RequestParam("author") String author,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam("available") boolean isAvailable,
            @RequestParam("type") BookType type,
            @RequestParam("edition") String edition,
            @RequestParam("dateOfPublish") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfPublish,
            @RequestParam("image") MultipartFile image
    ) {
        BookRequestDto bookRequestDto = new BookRequestDto(
            null,
            author,
            name,
            price,
            quantity,
            null,
            isAvailable,
            type,
            edition,
            dateOfPublish
        );
        Book created = bookUseCase.create(bookDtoMapper.mapFromRequest(bookRequestDto), image);
        return ResponseEntity
            .created(URI.create("/api/book/" + created.getId())).body(bookDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<BookResponseDto> update(@PathVariable UUID id, @RequestBody @Valid BookRequestDto bookRequestDto) {
        return ResponseEntity
            .ok(bookDtoMapper.mapToResponse(bookUseCase.update(id, bookDtoMapper.mapFromRequest(bookRequestDto))));
    }

    @DeleteMapping(value = "/delete/{id}")
    @CacheEvict(value = "books", allEntries = true)
    public ResponseEntity<BookResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(bookDtoMapper.mapToResponse(bookUseCase.delete(id)));
    }
}
