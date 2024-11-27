package com.app.library.application.gateways.rent;

import com.app.library.domain.entity.rent.Rent;

import java.util.List;
import java.util.UUID;

public interface RentGateway {
    List<Rent> getAll();
    Rent get(UUID id);
    Rent create(Rent rent);
    Rent update(Rent rent);
    Rent delete(UUID id);
}
