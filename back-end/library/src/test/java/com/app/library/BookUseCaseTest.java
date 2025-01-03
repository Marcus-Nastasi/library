package com.app.library;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.book.BookGateway;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.aws.FileManagerUseCase;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookPaginated;
import com.app.library.domain.entity.book.BookType;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.domain.entity.rent.Rent;
import com.app.library.domain.entity.rent.RentPaginated;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BookUseCaseTest {

    @Mock
    private BookGateway bookGateway;
    @Mock
    private FileManagerUseCase fileManagerUseCase;
    @Mock
    private RentGateway rentGateway;
    @Mock
    private MemberUseCase memberUseCase;
    @InjectMocks
    private BookUseCase bookUseCase;

    Book book1 = new Book(
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
    Book book2 = new Book(
        UUID.randomUUID(),
        "author2",
        "name2",
        1.99,
        5,
        null,
        false,
        BookType.JOURNAL,
        "3.5",
        LocalDate.of(2002, 5, 12)
    );
    List<Book> books = List.of(book1, book2);
    Rent rent1 = new Rent(
        UUID.randomUUID(),
        book1.getId(),
        LocalDate.now(),
        LocalDate.now().plusDays(7),
        UUID.randomUUID(),
        UUID.randomUUID(),
        false
    );
    Rent rent2 = new Rent(
        UUID.randomUUID(),
        book2.getId(),
        LocalDate.now(),
        LocalDate.now().plusDays(7),
        UUID.randomUUID(),
        UUID.randomUUID(),
        false
    );
    List<Rent> rents = List.of(rent1, rent2);
    RentPaginated rentPaginated = new RentPaginated(0, 10, 1, rents);

    @Test
    void getAllBooks() {
        BookPaginated bookPaginated = new BookPaginated(0, 10, 1, books);

        when(bookGateway.getAll(0, 10)).thenReturn(bookPaginated);

        assertEquals(bookPaginated, bookUseCase.getAll(0, 10));
        assertEquals(bookPaginated.getTotal(), bookUseCase.getAll(0, 10).getTotal());

        verify(bookGateway, times(2)).getAll(0, 10);
    }

    @Test
    void getBookById() {
        when(bookGateway.get(any(UUID.class))).thenReturn(book1);

        assertEquals(book1, bookUseCase.get(UUID.randomUUID()));
        assertEquals(book1.getAuthor(), bookUseCase.get(UUID.randomUUID()).getAuthor());
        assertDoesNotThrow(() -> bookUseCase.get(UUID.randomUUID()));

        verify(bookGateway, times(3)).get(any(UUID.class));
    }

    @Test
    void createBook() {
        when(fileManagerUseCase.upload(any(byte[].class), any(String.class))).thenReturn("image_url");
        when(bookGateway.save(any(Book.class))).thenReturn(book1);

        assertEquals(book1, bookUseCase.create(book1, new byte[0], ""));
        assertEquals(book1.getName(), bookUseCase.create(book1, new byte[0], "").getName());
        assertDoesNotThrow(() -> bookUseCase.create(book1, new byte[0], ""));

        verify(fileManagerUseCase, times(3)).upload(any(byte[].class), any(String.class));
        verify(bookGateway, times(6)).save(any(Book.class));
    }

    @Test
    void updateBook() {
        when(bookGateway.get(any(UUID.class))).thenReturn(book1);
        when(bookGateway.save(any(Book.class))).thenReturn(book2);

        assertEquals(book2, bookUseCase.update(UUID.randomUUID(), book2));
        assertEquals(book2.getName(), bookUseCase.update(UUID.randomUUID(), book1).getName());
        assertDoesNotThrow(() -> bookUseCase.update(UUID.randomUUID(), book1));

        verify(bookGateway, times(3)).save(any(Book.class));
    }

    @Test
    void deleteBook() {
        book1.setImage_url("image url");

        when(bookGateway.get(any(UUID.class))).thenReturn(book1);
        when(bookGateway.delete(any(UUID.class))).thenReturn(book1);

        when(rentGateway.getAllByBookId(any(UUID.class))).thenReturn(rents);
        when(rentGateway.delete(any(UUID.class))).thenReturn(rent1);

        doNothing().when(memberUseCase).decreaseIssueBook(any(UUID.class));
        doNothing().when(fileManagerUseCase).delete(anyString());

        assertEquals(book1, bookUseCase.delete(UUID.randomUUID()));
        assertEquals(book1.getName(), bookUseCase.delete(UUID.randomUUID()).getName());
        assertDoesNotThrow(() -> bookUseCase.delete(UUID.randomUUID()));

        verify(bookGateway, times(3)).delete(any(UUID.class));
    }

    @Test
    void decreaseQuantity() {
        int bookInitialQuantity = book1.getQuantity();
        book1.decreaseQuantity();

        when(bookGateway.get(any(UUID.class))).thenReturn(book1);
        when(bookGateway.get(book2.getId())).thenReturn(book2);
        when(bookGateway.save(any(Book.class))).thenReturn(book1);

        assertEquals(bookInitialQuantity - 1, book1.getQuantity());
        assertDoesNotThrow(() -> bookUseCase.decreaseQuantity(UUID.randomUUID()));
        assertThrows(ApplicationException.class, () -> bookUseCase.decreaseQuantity(book2.getId()));

        verify(bookGateway, times(3)).get(any(UUID.class));
        verify(bookGateway, times(1)).save(any(Book.class));
    }

    @Test
    void increaseQuantity() {
        int bookInitialQuantity = book1.getQuantity();
        book1.increaseQuantity();

        when(bookGateway.get(any(UUID.class))).thenReturn(book1);
        when(bookGateway.save(any(Book.class))).thenReturn(book1);

        assertEquals(bookInitialQuantity + 1, book1.getQuantity());
        assertDoesNotThrow(() -> bookUseCase.increaseQuantity(UUID.randomUUID()));

        verify(bookGateway, times(2)).get(any(UUID.class));
        verify(bookGateway, times(1)).save(any(Book.class));
    }
}
