package com.app.library.application.usecases.rent;

import com.app.library.application.exception.ApplicationException;
import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.application.usecases.book.BookUseCase;
import com.app.library.domain.entity.rent.Rent;
import com.app.library.domain.entity.rent.RentPaginated;

import java.time.LocalDate;
import java.util.UUID;

public class RentUseCase {
    private final RentGateway rentGateway;
    private final BookUseCase bookUseCase;

    public RentUseCase(RentGateway rentGateway, BookUseCase bookUseCase) {
        this.rentGateway = rentGateway;
        this.bookUseCase = bookUseCase;
    }

    public RentPaginated getAll(int page, int size) {
        return rentGateway.getAll(page, size);
    }

    public Rent get(UUID id) {
        return rentGateway.get(id);
    }

    public Rent create(Rent rent) {
        rent.setEmit_date(LocalDate.now());
        rent.setReturn_date(LocalDate.now().plusDays(7));
        rent.setReturned(false);
        bookUseCase.decreaseQuantity(rent.getBook_id());
        return rentGateway.create(rent);
    }

    public Rent update(UUID id, Rent rent) {
        Rent toUpdate = get(id);
        return rentGateway.update(toUpdate.updateDetails(rent));
    }

    public Rent delete(UUID id) {
        return rentGateway.delete(id);
    }

    public void returningRent(UUID id) {
        Rent rent = this.get(id);
        if (rent == null) throw new ApplicationException("rent not found");
        if (rent.isReturned()) throw new ApplicationException("rent already returned");
        rent.setReturned(true);
        bookUseCase.increaseQuantity(rent.getBook_id());
        update(id, rent);
    }
}
