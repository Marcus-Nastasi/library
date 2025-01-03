package com.app.library;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.application.usecases.rent.RentUseCase;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.book.BookType;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.UserRole;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberType;
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
public class RentUseCaseTest {

    @Mock
    private RentGateway rentGateway;
    @Mock
    private BookUseCase bookUseCase;
    @Mock
    private MemberUseCase memberUseCase;
    @InjectMocks
    private RentUseCase rentUseCase;

    Book book1 = new Book(UUID.randomUUID(), "Author 1", "Name 1", 11.99, 10, "image_url", true, BookType.REGULAR, "Edition 1", LocalDate.of(2004, 9, 11));
    Book book2 = new Book(UUID.randomUUID(), "Author 2", "Name 2", 25.99, 5, null, true, BookType.STUDY, "Edition 2", LocalDate.of(2004, 10, 11));

    Member member1 = new Member(UUID.randomUUID(), "Name 1", "Cpf 1", MemberType.REGULAR, LocalDate.now(), 0, 10, "phone 1");
    Member member2 = new Member(UUID.randomUUID(), "Name 2", "Cpf 2", MemberType.STUDENT, LocalDate.now(), 2, 10, "phone 2");

    Librarian librarian1 = new Librarian(UUID.randomUUID(), "name 1", "cpf 1", "Password 1", UserRole.ADMIN);

    Rent rent1 = new Rent(UUID.randomUUID(), book1.getId(), LocalDate.now(), LocalDate.now().plusDays(7), librarian1.getId(), member1.getId(), false);
    Rent rent2 = new Rent(UUID.randomUUID(), book2.getId(), LocalDate.now(), LocalDate.now().plusDays(7), librarian1.getId(), member2.getId(), false);

    List<Rent> rents = List.of(rent1, rent2);
    RentPaginated rentPaginated = new RentPaginated(0, 10, 1, rents);

    @Test
    void getAllRents() {
        when(rentGateway.getAll(0, 10)).thenReturn(rentPaginated);

        assertEquals(rentPaginated, rentUseCase.getAll(0, 10));
        assertEquals(rentPaginated.getTotal(), rentUseCase.getAll(0, 10).getTotal());
        assertDoesNotThrow(() -> rentUseCase.getAll(0, 10));

        verify(rentGateway, times(3)).getAll(0, 10);
    }

    @Test
    void getRent() {
        when(rentGateway.get(rent1.getId())).thenReturn(rent1);

        assertEquals(rent1, rentUseCase.get(rent1.getId()));
        assertEquals(rent1.getBook_id(), rentUseCase.get(rent1.getId()).getBook_id());
        assertDoesNotThrow(() -> rentUseCase.get(rent1.getId()));

        verify(rentGateway, times(3)).get(any(UUID.class));
    }

    @Test
    void getByMember() {
        when(rentGateway.getByMember(rent1.getMember_id(), 0, 10)).thenReturn(rentPaginated);

        assertEquals(rentPaginated, rentUseCase.getByMember(rent1.getMember_id(), 0, 10));
        assertEquals(rentPaginated.getData().size(), rentUseCase.getByMember(rent1.getMember_id(), 0, 10).getData().size());
        assertDoesNotThrow(() -> rentUseCase.getByMember(rent1.getMember_id(), 0, 10));

        verify(rentGateway, times(3)).getByMember(any(UUID.class), anyInt(), anyInt());
    }

    @Test
    void getAllByBook() {
        when(rentGateway.getAllByBookId(rent1.getBook_id())).thenReturn(rents);

        assertEquals(rents, rentUseCase.getAllByBook(rent1.getBook_id()));
        assertEquals(rents.size(), rentUseCase.getAllByBook(rent1.getBook_id()).size());
        assertDoesNotThrow(() -> rentUseCase.getAllByBook(rent1.getBook_id()));

        verify(rentGateway, times(3)).getAllByBookId(any(UUID.class));
    }

    @Test
    void getByBook() {
        when(rentGateway.getByBookId(rent1.getBook_id(), 0, 10)).thenReturn(rentPaginated);

        assertEquals(rentPaginated, rentUseCase.getByBook(rent1.getBook_id(), 0, 10));
        assertEquals(rents.size(), rentUseCase.getByBook(rent1.getBook_id(), 0, 10).getData().size());
        assertDoesNotThrow(() -> rentUseCase.getByBook(rent1.getBook_id(), 0, 10));

        verify(rentGateway, times(3)).getByBookId(any(UUID.class), anyInt(), anyInt());
    }

    @Test
    void create() {
        when(bookUseCase.get(book1.getId())).thenReturn(book1);
        when(bookUseCase.get(book2.getId())).thenReturn(null);
        when(memberUseCase.get(member1.getId())).thenReturn(member1);
        when(memberUseCase.get(member2.getId())).thenReturn(null);
        when(rentGateway.create(any(Rent.class))).thenReturn(rent1);
        doNothing().when(bookUseCase).decreaseQuantity(any(UUID.class));
        doNothing().when(memberUseCase).increaseIssueBook(any(UUID.class));

        assertEquals(rent1, rentUseCase.create(rent1));
        assertDoesNotThrow(() -> rentUseCase.create(rent1));
        assertThrows(ApplicationException.class, () -> rentUseCase.create(rent2));

        verify(bookUseCase, times(3)).get(any(UUID.class));
        verify(memberUseCase, times(3)).get(any(UUID.class));
        verify(rentGateway, times(2)).create(any(Rent.class));
    }

    @Test
    void update() {
        when(rentGateway.get(rent1.getId())).thenReturn(rent1);
        when(rentGateway.get(rent2.getId())).thenReturn(null);
        when(rentGateway.update(any(Rent.class))).thenReturn(rent1);

        assertEquals(rent1, rentUseCase.update(rent1.getId(), rent1));
        assertDoesNotThrow(() -> rentUseCase.update(rent1.getId(), rent1));
        assertThrows(ApplicationException.class, () -> rentUseCase.update(rent2.getId(), rent2));

        verify(rentGateway, times(3)).get(any(UUID.class));
        verify(rentGateway, times(2)).update(rent1);
    }

    @Test
    void delete() {
        when(rentGateway.get(any(UUID.class))).thenReturn(rent1);
        when(rentGateway.delete(rent1.getId())).thenReturn(rent1);

        assertEquals(rent1, rentUseCase.delete(rent1.getId()));
        assertDoesNotThrow(() -> rentUseCase.delete(rent1.getId()));

        verify(rentGateway, times(2)).delete(any(UUID.class));
    }

    @Test
    void returningRent() {
        when(rentGateway.get(rent1.getId())).thenReturn(rent1);
        when(rentGateway.get(rent2.getId())).thenReturn(null);
        when(rentGateway.update(any(Rent.class))).thenReturn(rent1);
        doNothing().when(bookUseCase).increaseQuantity(any(UUID.class));
        doNothing().when(memberUseCase).decreaseIssueBook(any(UUID.class));

        assertDoesNotThrow(() -> rentUseCase.returningRent(rent1.getId()));
        assertThrows(ApplicationException.class, () -> rentUseCase.returningRent(rent2.getId()));

        rent1.setReturned(true);

        assertThrows(ApplicationException.class, () -> rentUseCase.returningRent(rent1.getId()));

        verify(rentGateway, times(4)).get(any(UUID.class));
        verify(rentGateway, times(1)).update(any(Rent.class));
    }
}
