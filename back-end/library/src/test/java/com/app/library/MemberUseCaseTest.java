package com.app.library;

import com.app.library.application.gateways.member.MemberGateway;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;
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
public class MemberUseCaseTest {

    @Mock
    private MemberGateway memberGateway;
    @InjectMocks
    private MemberUseCase memberUseCase;

    Member member1 = new Member();
    Member member2 = new Member();

    List<Member> members = List.of(member1, member2);

    @Test
    void getAllMembers() {
        MemberPaginated memberPaginated = new MemberPaginated(0, 10, 1, members);
        when(memberGateway.getAll(0, 10)).thenReturn(memberPaginated);

        assertEquals(memberPaginated, memberUseCase.getAll(0, 10));
        assertEquals(memberPaginated.getTotal(), memberUseCase.getAll(0, 10).getTotal());
        assertDoesNotThrow(() -> memberUseCase.getAll(0, 10));

        verify(memberGateway, times(3)).getAll(0, 10);
    }

    @Test
    void getMember() {
        when(memberGateway.get(any(UUID.class))).thenReturn(member1);

        assertEquals(member1, memberUseCase.get(UUID.randomUUID()));
        assertDoesNotThrow(() -> memberUseCase.get(UUID.randomUUID()));

        verify(memberGateway, times(2)).get(any(UUID.class));
    }

    @Test
    void getByCpf() {
        when(memberGateway.getByCpf(any(String.class))).thenReturn(member1);

        assertEquals(member1, memberUseCase.getByCpf(UUID.randomUUID().toString()));
        assertDoesNotThrow(() -> memberUseCase.getByCpf(UUID.randomUUID().toString()));

        verify(memberGateway, times(2)).getByCpf(any(String.class));
    }

    @Test
    void createMember() {
        when(memberGateway.create(any(Member.class))).thenReturn(member1);

        assertEquals(member1, memberUseCase.create(member1));
        assertDoesNotThrow(() -> memberUseCase.create(member1));

        verify(memberGateway, times(2)).create(any(Member.class));
    }

    @Test
    void updateMember() {
        when(memberGateway.get(any(UUID.class))).thenReturn(member1);
        when(memberGateway.update(any(Member.class))).thenReturn(member2);

        member1.setId(UUID.randomUUID());
        member1.setName("");
        member1.setCpf(UUID.randomUUID().toString());

        member2.setId(UUID.randomUUID());
        member2.setName("");
        member2.setCpf(UUID.randomUUID().toString());

        assertEquals(member2, memberUseCase.update(UUID.randomUUID(), member1));
        assertNotEquals(member1, memberUseCase.update(UUID.randomUUID(), member1));
        assertDoesNotThrow(() -> memberUseCase.update(UUID.randomUUID(), member1));

        verify(memberGateway, times(3)).update(any(Member.class));
    }

    @Test
    void deleteMember() {
        when(memberGateway.get(any(UUID.class))).thenReturn(member1);
        when(memberGateway.delete(any(UUID.class))).thenReturn(member1);

        assertEquals(member1, memberUseCase.delete(UUID.randomUUID()));
        assertDoesNotThrow(() -> memberUseCase.delete(UUID.randomUUID()));

        verify(memberGateway, times(2)).delete(any(UUID.class));
    }

    @Test
    void increaseIssueBook() {
        member1.setId(UUID.randomUUID());
        member1.setCpf(UUID.randomUUID().toString());
        member1.setName("");
        member1.setBooksIssued(0);
        member2.setCpf(UUID.randomUUID().toString());
        member2.setName("");
        int memberInitialBooksIssued = member1.getBooksIssued();

        assertThrows(DomainException.class, () -> member1.increaseIssueBook());

        member1.setBooksLimit(10);

        member1.increaseIssueBook();

        when(memberGateway.get(any(UUID.class))).thenReturn(member1);
        when(memberGateway.get(member2.getId())).thenReturn(member2);
        when(memberGateway.update(any(Member.class))).thenReturn(member1);

        assertEquals(memberInitialBooksIssued + 1, member1.getBooksIssued());
        assertDoesNotThrow(() -> memberUseCase.increaseIssueBook(UUID.randomUUID()));
        assertThrows(DomainException.class, () -> memberUseCase.increaseIssueBook(member2.getId()));

        verify(memberGateway, times(2)).get(any(UUID.class));
        verify(memberGateway, times(1)).update(any(Member.class));
    }

    @Test
    void decreaseIssueBook() {
        member1.setId(UUID.randomUUID());
        member1.setCpf(UUID.randomUUID().toString());
        member1.setName("");
        member1.setBooksIssued(5);
        member1.setBooksLimit(10);
        member2.setCpf(UUID.randomUUID().toString());
        member2.setName("");
        member2.setBooksIssued(0);
        int memberInitialBooksIssued = member1.getBooksIssued();
        member1.decreaseIssueBook();

        when(memberGateway.get(any(UUID.class))).thenReturn(member1);
        when(memberGateway.get(member2.getId())).thenReturn(member2);
        when(memberGateway.update(any(Member.class))).thenReturn(member1);

        assertEquals(memberInitialBooksIssued - 1, member1.getBooksIssued());
        assertDoesNotThrow(() -> memberUseCase.decreaseIssueBook(UUID.randomUUID()));
        assertThrows(DomainException.class, () -> memberUseCase.decreaseIssueBook(member2.getId()));

        verify(memberGateway, times(2)).get(any(UUID.class));
        verify(memberGateway, times(1)).update(any(Member.class));
    }
}
