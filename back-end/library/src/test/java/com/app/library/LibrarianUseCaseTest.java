package com.app.library;

import com.app.library.application.gateways.librarian.LibrarianGateway;
import com.app.library.application.usecases.librarian.LibrarianUseCase;
import com.app.library.application.usecases.security.PasswordUseCase;
import com.app.library.domain.entity.librarian.Librarian;
import com.app.library.domain.entity.librarian.LibrarianPaginated;
import com.app.library.domain.entity.librarian.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LibrarianUseCaseTest {
    @Mock
    private LibrarianGateway librarianGateway;
    @Mock
    private PasswordUseCase passwordUseCase;
    @InjectMocks
    private LibrarianUseCase librarianUseCase;

    Librarian librarian1 = new Librarian();
    Librarian librarian2 = new Librarian();
    List<Librarian> librarians = List.of(librarian1, librarian2);

    @Test
    void getAllLibrarians() {
        LibrarianPaginated librarianPaginated = new LibrarianPaginated(0, 10, 1, librarians);
        when(librarianGateway.getAll(0, 10)).thenReturn(librarianPaginated);

        assertEquals(librarianPaginated, librarianUseCase.getAll(0, 10));
        assertEquals(librarianPaginated.getTotal(), librarianUseCase.getAll(0, 10).getTotal());
        assertDoesNotThrow(() -> librarianUseCase.getAll(0, 10));

        verify(librarianGateway, times(3)).getAll(0, 10);
    }

    @Test
    void get() {
        when(librarianGateway.get(any(UUID.class))).thenReturn(librarian1);

        librarian1.setCpf("1234");

        assertEquals(librarian1, librarianUseCase.get(UUID.randomUUID()));
        assertEquals(librarian1.getCpf(), librarianUseCase.get(UUID.randomUUID()).getCpf());
        assertDoesNotThrow(() -> librarianUseCase.get(UUID.randomUUID()));

        verify(librarianGateway, times(3)).get(any(UUID.class));
    }

    @Test
    void createLibrarian() {
        when(passwordUseCase.encode(any(String.class))).thenReturn("1234");
        when(librarianGateway.create(any(Librarian.class))).thenReturn(librarian1);

        librarian1.setPassword("1234");

        assertEquals(librarian1, librarianUseCase.create(librarian1));
        assertEquals(UserRole.USER, librarianUseCase.create(librarian1).getRole());
        assertDoesNotThrow(() -> librarianUseCase.create(librarian1));

        verify(librarianGateway, times(3)).create(any(Librarian.class));
    }

    @Test
    void updateLibrarian() {
        when(passwordUseCase.encode(any(String.class))).thenReturn("1234");
        when(librarianGateway.get(any(UUID.class))).thenReturn(librarian1);
        when(librarianGateway.update(any(Librarian.class))).thenReturn(librarian2);

        librarian1.setId(UUID.randomUUID());
        librarian1.setCpf(UUID.randomUUID().toString());
        librarian1.setPassword("1234");
        librarian2.setRole(UserRole.USER);

        assertEquals(librarian2, librarianUseCase.update(librarian1.getId(), librarian1));
        assertEquals(UserRole.USER, librarianUseCase.update(librarian1.getId(), librarian1).getRole());
        assertDoesNotThrow(() -> librarianUseCase.update(librarian1.getId(), librarian1));

        verify(librarianGateway, times(3)).update(any(Librarian.class));
    }

    @Test
    void deleteLibrarian() {
        when(librarianGateway.get(any(UUID.class))).thenReturn(librarian1);
        when(librarianGateway.delete(any(UUID.class))).thenReturn(librarian1);
        librarian1.setId(UUID.randomUUID());

        assertEquals(librarian1, librarianUseCase.delete(librarian1.getId()));
        assertDoesNotThrow(() -> librarianUseCase.delete(librarian1.getId()));

        verify(librarianGateway, times(2)).delete(any(UUID.class));
    }
}
