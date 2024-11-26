package com.app.library;

import com.app.library.adapters.output.book.JpaBookRepo;
import com.app.library.domain.entities.book.Book;
import com.app.library.domain.entities.book.BookType;
import com.app.library.domain.usecases.book.BookUseCase;
import com.app.library.infrastructure.entities.book.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookTest {

    @Mock
    private JpaBookRepo jpaBookRepo;
    @InjectMocks
    private BookUseCase bookUseCase;

    Book book = new Book(
        UUID.randomUUID(),
        "author",
        "name",
        16.99,
        5,
        null,
        true,
        BookType.JOURNAL,
        "3.5",
        LocalDate.of(2002, 5, 12)
    );

    BookEntity bookEntity = new Book(
            UUID.randomUUID(),
            "author",
            "name",
            16.99,
            5,
            null,
            true,
            BookType.JOURNAL,
            "3.5",
            LocalDate.of(2002, 5, 12)
    ).mapToBookEntity();

    @Test
    void createBook() {
        assertDoesNotThrow(() ->
                bookUseCase.create("", "", 1.2, 2, "", false, BookType.REGULAR, "", LocalDate.of(2004, 10, 10)));
        verify(jpaBookRepo, times(1)).save(any(BookEntity.class));
    }

    /*
    @Test
    void deleteBook() {
        when(jpaBookRepo.findById());
    }*/
}
