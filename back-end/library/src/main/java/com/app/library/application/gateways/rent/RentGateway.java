package com.app.library.application.gateways.rent;

import com.app.library.domain.entity.rent.Rent;
import com.app.library.domain.entity.rent.RentPaginated;

import java.util.List;
import java.util.UUID;

public interface RentGateway {

    RentPaginated getAll(int page, int size);

    Rent get(UUID id);

    Rent create(Rent rent);

    Rent update(Rent rent);

    Rent delete(UUID id);

    RentPaginated getByMember(UUID member_id, int page, int size);

    RentPaginated getByBookId(UUID book_id, int page, int size);

    List<Rent> getAllByBookId(UUID book_id);
}
