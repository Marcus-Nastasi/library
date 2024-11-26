package com.app.library.adapters.input.book;

import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.domain.entity.book.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {
    private final BookUseCase bookUseCase;

    public BookController(BookUseCase bookUseCase) {
        this.bookUseCase = bookUseCase;
    }

    @GetMapping()
    public List<Book> getAll() {
        return bookUseCase.getAll();
    }
}
