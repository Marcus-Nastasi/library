package com.app.library.application.usecases.rent;

import com.app.library.application.gateways.rent.RentGateway;
import com.app.library.domain.entity.rent.Rent;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RentUseCase {
    private final RentGateway rentGateway;

    public RentUseCase(RentGateway rentGateway) {
        this.rentGateway = rentGateway;
    }

    public List<Rent> getAll() {
        return rentGateway.getAll();
    }

    public Rent get(UUID id) {
        return rentGateway.get(id);
    }

    public Rent create(Rent rent) {
        rent.setEmit_date(LocalDate.now());
        rent.setReturn_date(LocalDate.now().plusDays(7));
        return rentGateway.create(rent);
    }

    public Rent update(UUID id, Rent rent) {
        Rent toUpdate = get(id);
        return rentGateway.update(toUpdate.updateDetails(rent));
    }

    public Rent delete(UUID id) {
        Rent rent = get(id);
        rentGateway.delete(id);
        return rent;
    }
}
