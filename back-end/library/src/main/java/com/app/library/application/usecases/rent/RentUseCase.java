package com.app.library.application.usecases.rent;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.book.Book;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.rent.Rent;
import com.app.library.domain.entity.rent.RentPaginated;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RentUseCase {

    private final RentGateway rentGateway;
    private final BookUseCase bookUseCase;
    private final MemberUseCase memberUseCase;

    public RentUseCase(RentGateway rentGateway, BookUseCase bookUseCase, MemberUseCase memberUseCase) {
        this.rentGateway = rentGateway;
        this.bookUseCase = bookUseCase;
        this.memberUseCase = memberUseCase;
    }

    public RentPaginated getAll(int page, int size) {
        return rentGateway.getAll(page, size);
    }

    public Rent get(UUID id) {
        return rentGateway.get(id);
    }

    public RentPaginated getByMember(UUID member_id, int page, int size) {
        return rentGateway.getByMember(member_id, page, size);
    }

    public RentPaginated getByBook(UUID book_id, int page, int size) {
        return rentGateway.getByBookId(book_id, page, size);
    }

    public List<Rent> getAllByBook(UUID book_id) {
        return rentGateway.getAllByBookId(book_id);
    }

    public Rent create(Rent rent) {
        Book book = bookUseCase.get(rent.getBook_id());
        Member member = memberUseCase.get(rent.getMember_id());
        if (book == null || member == null) throw new ApplicationException("member or book not found");
        if (!book.isAvailable() || book.getQuantity() < 1) throw new ApplicationException("no books available");
        if (member.getBooksIssued() == member.getBooksLimit()) throw new ApplicationException("already on book's limit");
        rent.setEmit_date(LocalDate.now());
        rent.setReturn_date(LocalDate.now().plusDays(7));
        rent.setReturned(false);
        bookUseCase.decreaseQuantity(book.getId());
        memberUseCase.increaseIssueBook(member.getId());
        return rentGateway.create(rent);
    }

    public Rent update(UUID id, Rent rent) {
        Rent toUpdate = get(id);
        if (toUpdate == null) throw new ApplicationException("rent not found");
        return rentGateway.update(toUpdate.updateDetails(rent));
    }

    public Rent delete(UUID id) {
        Rent toDelete = get(id);
        if (toDelete == null) throw new ApplicationException("rent not found");
        return rentGateway.delete(id);
    }

    public void returningRent(UUID id) {
        Rent rent = get(id);
        if (rent == null) throw new ApplicationException("rent not found");
        if (rent.isReturned()) throw new ApplicationException("rent already returned");
        rent.setReturned(true);
        bookUseCase.increaseQuantity(rent.getBook_id());
        memberUseCase.decreaseIssueBook(rent.getMember_id());
        update(id, rent);
    }
}
